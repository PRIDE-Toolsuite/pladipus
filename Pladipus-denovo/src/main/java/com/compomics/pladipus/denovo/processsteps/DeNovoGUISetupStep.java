package com.compomics.pladipus.denovo.processsteps;

import com.compomics.pladipus.core.control.util.PladipusFileDownloadingService;
import com.compomics.pladipus.core.model.processing.ProcessingStep;
import java.io.File;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Kenneth Verheggen
 */
public class DeNovoGUISetupStep extends ProcessingStep {

    /**
     * the temp folder for the entire processing
     */
    private final File tempResources;

    public DeNovoGUISetupStep() {
        tempResources = new File(System.getProperty("user.home") + "/.compomics/pladipus/temp/DeNovoGUI/resources");
    }

    @Override
    public boolean doAction() throws Exception {
        System.out.println("Running " + this.getClass().getName());
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
        return true;
    }

    private void initialiseInputFiles() throws Exception {
        //original
        String inputPath = parameters.get("spectrum_files");
        String paramPath = parameters.get("id_params");

        if (inputPath.toLowerCase().endsWith(".mgf")) {
            parameters.put("spectrum_files", PladipusFileDownloadingService.downloadFile(inputPath, tempResources).getAbsolutePath());
        } else {
            parameters.put("spectrum_files", PladipusFileDownloadingService.downloadFolder(inputPath, tempResources).getAbsolutePath());
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
