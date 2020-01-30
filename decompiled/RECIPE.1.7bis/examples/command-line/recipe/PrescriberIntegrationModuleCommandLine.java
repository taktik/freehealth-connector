package org.taktik.connector.business.recipe.prescriber;
/**
 * Copyright (C) 2010 Recip-e
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import be.business.connector.common.AbstractCommandLine;
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipe.prescriber.mock.PrescriberIntegrationModuleMock;
import be.recipe.services.prescriber.GetPrescriptionForPrescriberResult;
import be.recipe.services.prescriber.ListFeedbackItem;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.*;


/**
 * The Class PrescriberIntegrationModuleCommandLine.
 * <p>
 * This class is a sample command line class using Recipe Prescription Module
 */
public class PrescriberIntegrationModuleCommandLine extends AbstractCommandLine{

    /**
     * The Constant OUT.
     */
    private static final PrintStream OUT = System.out;

    /**
     * The main method.
     *
     * @param args the arguments
     * @throws IntegrationModuleException the integration module exception
     */
    public static void main(String[] args) {
        try {

            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("null")) {
                    args[i] = null;
                }
            }

            if (args.length == 5 && args[0].equals("createPrescription")) {

                OUT.println(new Boolean(args[1]) + " " + args[2] + " " +
                        Arrays.toString(loadFile(args[3]))
                        + " " + args[4]);
                String rid = getModule().createPrescription(
                        new Boolean(args[1]), args[2],
                        loadFile(args[3]), args[4]);
                OUT.println("Prescription successfully created : " + rid);

            } else if (args.length == 6
                    && args[0].equals("createPrescriptionsSequentially")) {
                Date testStart = Calendar.getInstance().getTime();

                int nrOfPrescriptions = Integer.parseInt(args[5]);
                List<String> createdPrescriptions = new LinkedList<String>();

                int i = 0;
                PrescriberIntegrationModuleImpl module = getModule();
                for (; i < nrOfPrescriptions; i++) {
                    OUT.println("Creating prescription with index " + i);

                    String rid = module.createPrescription(
                            new Boolean(args[1]), args[2],
                            loadFile(args[3]), args[4]);
                    OUT.println("Prescription " + i
                            + "  successfully created : " + rid);
                    createdPrescriptions.add(rid);
                }

                Date testEnd = Calendar.getInstance().getTime();
                System.out.println((i - 1)
                        + " precriptions were created between " + testStart
                        + " until " + testEnd);
                OUT.println("Summary of created prescriptions: ");
                for (String rid : createdPrescriptions) {
                    OUT.println("\b" + rid);
                }

            } else if (args.length == 2 && args[0].equals("getPrescription")) {

                GetPrescriptionForPrescriberResult p = getModule()
                        .getPrescription(args[1]);

                OUT.println("Creation Date : " + p.getCreationDate().getTime());
                OUT.println("Patient : " + p.getPatientId());
                OUT.println("FeedbackAllowed : " + p.getFeedbackAllowed());
                OUT.println("Prescription :\n"
                        + new String(p.getPrescription()));

            } else if (args.length == 2 && args[0].equals("listFeedback")) {

                PrescriberIntegrationModuleImpl module = getModule();
                module.setPersonalPassword(args[1]);

                List<ListFeedbackItem> fbs = module.listFeedback(true);
                int i = 0;
                for (ListFeedbackItem it : fbs) {
                    OUT.println("--------------------------------------------------");
                    OUT.println("Feedback : " + (++i));
                    OUT.println("Sent by : " + it.getSentBy());
                    OUT.print(it.getSentDate());
                    OUT.println("Sent Date : " + (it.getSentDate() != null ? it
                            .getSentDate().getTime() : null));
                    OUT.println("For Prescription : " + it.getRid());
                    OUT.println("Content :\n" + new String(it.getContent()));
                }
                if (fbs.size() == 0) {
                    OUT.println("No pending feedback");
                }
            } else if (args.length == 3 && args[0].equals("revokePrescription")) {

                getModule().revokePrescription(args[1], args[2]);

                OUT.println("Prescription successfully revoked!");

            } else if (args.length == 4 && args[0].equals("sendNotification")) {

                getModule().sendNotification(loadFile(args[1]),
                        args[2], args[3]);

                OUT.println("Notification sent!");

            } else if (args.length == 5
                    && args[0].equals("sendNotificationsSequentially")) {

                int nrOfNotifications = Integer.parseInt(args[4]);

                int i = 0;
                PrescriberIntegrationModuleImpl module = getModule();
                for (; i < nrOfNotifications; i++) {
                    OUT.println("Creating notification with index " + i);

                    module.sendNotification(loadFile(args[1]), args[2], args[3]);
                    OUT.println("Notification sent!");
                }

                System.out.println(i + "notifications were created between ");

            } else if (args.length == 3 && args[0].equals("updateFeedbackFlag")) {

                getModule().updateFeedbackFlag(args[1],
                        Boolean.getBoolean(args[2]));

                OUT.println("Feedback flag updated!");

            } else if (args.length == 2
                    && args[0].equals("listOpenPrescription")) {

                List<String> rids = getModule().listOpenPrescription(args[1]);
                OUT.println("Open prescriptions: ");
                for (String rid : rids) {
                    OUT.println(rid);
                }
            } else if (args.length == 1
                    && args[0].equals("prompt")) {

                String command = null;
                InputStreamReader converter = new InputStreamReader(System.in);
                BufferedReader in = new BufferedReader(converter);
                System.out.print("Prompt mode started. Input your command:\n$");
                while ((command = in.readLine()) != null) {
                    if ("q".equalsIgnoreCase(command) ||
                            "quit".equalsIgnoreCase(command)) {
                        System.exit(0);
                    }
                    main(command.split(" "));
                    System.out.print("\n$");
                }

            } else {
                if (args.length > 0) {
                    OUT.println("ERROR : Invalid number of arguments");
                }
                showHelp();
            }

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }


    private static PrescriberIntegrationModuleImpl module = null;



    private static PrescriberIntegrationModuleImpl initModule() throws Exception {

        initializeSystem();
        if ("true".equals(System.getProperty("mock"))) {
            OUT.println("Using a mock module");
            return new PrescriberIntegrationModuleMock();
        }
        return new PrescriberIntegrationModuleImpl();
    }

    /**
     * Lazy getter for the module.
     *
     * @return the module
     * @throws IntegrationModuleException the integration module exception
     */
    private static PrescriberIntegrationModuleImpl getModule() throws Exception {
        if (module == null) {
            module = initModule();
        }
        return module;
    }

    /**
     * Show help.
     */
    private static void showHelp() {
        StringBuffer sb = new StringBuffer();

        OUT.println("Usage :  java <classpath> <-Doption1=option2> "
                + PrescriberIntegrationModuleCommandLine.class.getName()
                + " [action] <arg1>...");

        OUT.println("");
        OUT.println("------------ Action available ------------");
        OUT.println(" createPrescription <arg1> <arg2> <arg3> <arg4>");
        OUT.println("	  arg1 : feedback flag");
        OUT.println("	  arg2 : patient NISS number");
        OUT.println("	  arg3 : path the the XML prescription");
        OUT.println("	  arg4 : type of prescription");
        OUT.println("  ex : createPrescription true 123456789 prescription1.xml PP");
        OUT.println("");

        OUT.println(" getPrescription <arg1>");
        OUT.println("	  arg1 : RID");
        OUT.println("  ex : getPrescription BEPPabcdefgh");
        OUT.println("");

        OUT.println(" listFeedback <arg1>");
        OUT.println("	  arg1 : private key pasword");
        OUT.println("");

        OUT.println(" listOpenPrescription");
        OUT.println("	  arg1 : PatientId");
        OUT.println("  ex : listOpenPrescription 72081061175");
        OUT.println("");

        OUT.println(" revokePrescription");
        OUT.println("	  arg1 : RID");
        OUT.println("  ex : revokePrescription BEPPabcdefgh");
        OUT.println("");

        OUT.println(" sendNotification <arg1> <arg2> <arg3>");
        OUT.println("	  arg1 : path the the XML notification");
        OUT.println("	  arg2 : patient NISS number");
        OUT.println("	  arg2 : pharmacist INAMI/RIZIV number");
        OUT.println("  ex : sendNotification Feedback1.xml 123456789 987654321");
        OUT.println("");

        OUT.println(" updateFeedbackFlag <arg1> <arg2>");
        OUT.println("	  arg1 : rid");
        OUT.println("	  arg2 : boolean that (dis)allow feedback");
        OUT.println("  ex : updateFeedbackFlag BEPPaabbccdd false");
        OUT.println("");
        OUT.println("-------------- OPTIONS ------------");
        OUT.println(" -Dconfig=<path to the config file> : specify a config file");
        OUT.println(" -Dmock=true : use mock services");
        OUT.println(" -Dsession=<path to Xml session file> : specify a session");
        OUT.println(" -DsystemKeystore=<path to system keystore properties file> : specify the system keystore properties");

        OUT.println(sb.toString());
    }

}
