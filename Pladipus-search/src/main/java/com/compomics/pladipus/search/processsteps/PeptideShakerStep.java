/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compomics.pladipus.search.processsteps;

import com.compomics.pladipus.core.control.engine.ProcessingEngine;
import com.compomics.pladipus.core.control.engine.callback.CallbackNotifier;
import com.compomics.pladipus.core.control.runtime.diagnostics.memory.MemoryWarningSystem;
import com.compomics.pladipus.core.control.util.JarLookupService;
import com.compomics.pladipus.core.control.util.PladipusFileDownloadingService;
import com.compomics.pladipus.core.control.util.ZipUtils;
import com.compomics.pladipus.core.model.enums.AllowedPeptideShakerFollowUpParams;
import com.compomics.pladipus.core.model.enums.AllowedPeptideShakerParams;
import com.compomics.pladipus.core.model.enums.AllowedPeptideShakerReportParams;
import com.compomics.pladipus.core.model.feedback.Checkpoint;
import com.compomics.pladipus.core.model.processing.ProcessingStep;
import com.compomics.pladipus.search.checkpoints.PeptideShakerCheckPoints;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author Kenneth Verheggen
 */
public class PeptideShakerStep extends ProcessingStep {

    private static final File temp_peptideshaker_output = new File(System.getProperty("user.home") + "/pladipus/temp/search/PeptideShaker");

    private static final Logger LOGGER = Logger.getLogger(PeptideShakerStep.class);
    private File temp_peptideshaker_cps;

    public PeptideShakerStep() {

    }

    private List<String> constructArguments() throws IOException {
        File peptideShakerJar = getJar();
        ArrayList<String> cmdArgs = new ArrayList<>();
        cmdArgs.add("java");
        cmdArgs.add("-Xmx" + MemoryWarningSystem.getAllowedRam() + "M");
        cmdArgs.add("-cp");
        cmdArgs.add(peptideShakerJar.getAbsolutePath());
        cmdArgs.add("eu.isas.peptideshaker.cmd.PeptideShakerCLI");
        //check if folder exists      
        File outputFolder = new File(parameters.get("output_folder"));
        outputFolder.mkdirs();
//check if reports are requested
        if (parameters.containsKey("reports") && !parameters.containsKey("out_reports")) {
            File outputReportFolder = new File(outputFolder, "reports");
            if (outputReportFolder.mkdirs()) {
                parameters.put("out_reports", outputReportFolder.getAbsolutePath());
            }
        }

        for (AllowedPeptideShakerParams aParameter : AllowedPeptideShakerParams.values()) {
            if (parameters.containsKey(aParameter.getId())) {
                cmdArgs.add("-" + aParameter.getId());
                cmdArgs.add(parameters.get(aParameter.getId()));
            } else if (aParameter.isMandatory()) {
                throw new IllegalArgumentException("Missing mandatory parameter : " + aParameter.id);
            }
        }
        //also add these for other possible CLI's?
        for (AllowedPeptideShakerFollowUpParams aParameter : AllowedPeptideShakerFollowUpParams.values()) {
            if (parameters.containsKey(aParameter.getId())) {
                cmdArgs.add("-" + aParameter.getId());
                cmdArgs.add(parameters.get(aParameter.getId()));
            }
        }
        for (AllowedPeptideShakerReportParams aParameter : AllowedPeptideShakerReportParams.values()) {
            if (parameters.containsKey(aParameter.getId())) {
                cmdArgs.add("-" + aParameter.getId());
                cmdArgs.add(parameters.get(aParameter.getId()));
            }
        }

        return cmdArgs;
    }

    @Override
    public boolean doAction() throws Exception, Exception {
        LOGGER.info("Running Peptide Shaker");
        File peptideShakerJar = getJar();

        if (temp_peptideshaker_output.exists()) {
            FileUtils.deleteDirectory(temp_peptideshaker_output);
        }
        temp_peptideshaker_output.mkdirs();

        String experiment = "output";

        if (parameters.containsKey("experiment")) {
            experiment = parameters.get("experiment");
        }

        if (parameters.containsKey("output_folder")) {
            temp_peptideshaker_cps = new File(temp_peptideshaker_output.getAbsolutePath() + "/" + experiment + ".cpsx");
            parameters.put("out", temp_peptideshaker_cps.getAbsolutePath());
        }
        File real_output_folder = new File(parameters.get("output_folder"));

        List<String> constructArguments = constructArguments();
        //add callback notifier for more detailed printouts of the processing
        CallbackNotifier callbackNotifier = getCallbackNotifier();
        for (PeptideShakerCheckPoints aCheckPoint : PeptideShakerCheckPoints.values()) {
            callbackNotifier.addCheckpoint(new Checkpoint(aCheckPoint.getLine(), aCheckPoint.getFeedback()));
        }
        new ProcessingEngine().startProcess(peptideShakerJar, constructArguments, callbackNotifier);
        cleanupAndSave(real_output_folder);
        return true;
    }

    public File getJar() throws IOException {
        //check if this is possible in another way...
        File toolFolder = new File(System.getProperties().getProperty("user.home") + "/pladipus/tools");
        toolFolder.mkdirs();
        //check if searchGUI already exists?
        File temp = new File(toolFolder, "PeptideShaker");
        if (!temp.exists()) {
            File peptideshakerFile = PladipusFileDownloadingService.downloadFile(parameters.get("PeptideShaker"), toolFolder);
            if (peptideshakerFile.getName().endsWith(".zip")) {
                ZipUtils.unzipArchive(peptideshakerFile, temp);
            }
        }
        return JarLookupService.lookupFile("PeptideShaker-.*.jar", temp);
    }

    public boolean aVersionExistsLocal() {
        //TODO insert installer code here in case PeptideShaker was not included????
        return true;
    }

    private void cleanupAndSave(File resultFolder) throws Exception {
        ZipUtils.zipFolder(temp_peptideshaker_output, new File(resultFolder, resultFolder.getName() + ".zip"));
    }

    @Override
    public String getDescription() {
        return "Running PeptideShaker";
    }
}
