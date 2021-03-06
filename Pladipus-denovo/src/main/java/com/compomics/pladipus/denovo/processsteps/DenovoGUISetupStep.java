/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compomics.pladipus.denovo.processsteps;

import com.compomics.pladipus.core.control.util.PladipusFileDownloadingService;
import com.compomics.pladipus.core.model.exception.UnspecifiedPladipusException;
import com.compomics.pladipus.core.model.processing.ProcessingStep;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/**
 *
 * @author Kenneth Verheggen
 */
public class DenovoGUISetupStep extends ProcessingStep {

    /**
     * the temp folder for the entire processing
     */
    private final File tempResources;

    public DenovoGUISetupStep() {
        tempResources = new File(System.getProperty("user.home") + "/.compomics/pladipus/temp/SearchGUI/resources");
    }

    @Override
    public boolean doAction() throws UnspecifiedPladipusException {
        System.out.println("Running " + this.getClass().getName());
        try {
        if (tempResources.exists()) {
            for (File aFile : tempResources.listFiles()) {
                if (aFile.exists()) {
                    if (aFile.isFile()) {
                        aFile.delete();
                    } else {
                        FileUtils.deleteDirectory(aFile);
                    }
                }
            }
        } else {
            tempResources.mkdirs();
        }
        initialiseInputFiles();
    } catch (IOException e){
           throw new UnspecifiedPladipusException(e);
        }
        return true;
    }

    private void initialiseInputFiles() throws IOException {
        //original
        String inputPath = parameters.get("spectrum_files");
        String paramPath = parameters.get("id_params");

        if (inputPath.toLowerCase().endsWith(".mgf")) {
            parameters.put("spectrum_files", PladipusFileDownloadingService.downloadFile(inputPath, tempResources).getAbsolutePath());
        } else {
            parameters.put("spectrum_files", PladipusFileDownloadingService.downloadFile(inputPath, tempResources).getAbsolutePath());
        }

        parameters.put("id_params", PladipusFileDownloadingService.downloadFile(paramPath, tempResources).getAbsolutePath());

        //output
        File outputFolder = new File(parameters.get("output_folder") + "/" + parameters.get("title"));
        outputFolder.mkdirs();
        parameters.put("output_folder", outputFolder.getAbsolutePath());
    }

    @Override
    public String getDescription() {
        return "Initialisation of the search process";
    }

}
