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
package be.business.connector.recipe.prescriber;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import be.business.connector.common.AbstractCommandLine;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.recipe.services.core.Page;
import be.recipe.services.prescriber.GetPrescriptionForPrescriberResult;
import be.recipe.services.prescriber.ListFeedbackItem;
import be.recipe.services.prescriber.ListOpenRidsParam;
import be.recipe.services.prescriber.ListOpenRidsResult;
import be.recipe.services.prescriber.ListRidsHistoryParam;
import be.recipe.services.prescriber.ListRidsHistoryResult;
import be.recipe.services.prescriber.ListRidsHistoryResultItem;
import be.recipe.services.prescriber.PutVisionParam;

/**
 * The Class PrescriberIntegrationModuleCommandLine.
 * <p>
 * This class is a sample command line class using Recipe Prescription Module
 */
public class PrescriberIntegrationModuleCommandLine extends AbstractCommandLine {

	/**
	 * The Constant OUT.
	 */
	private static final PrintStream OUT = System.out;

	/**
	 * The instance of {@link PrescriberIntegrationModule}
	 */
	private static PrescriberIntegrationModuleV4 module = null;

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 * @throws IntegrationModuleException
	 *             the integration module exception
	 */
	public static void main(final String[] args) {
		try {
			initializeSystem();
			for (int i = 0; i < args.length; i++) {
				if (args[i].equals("null")) {
					args[i] = null;
				}
			}

			if (args.length == 5 && args[0].equals("createPrescription")) {

				OUT.println(new Boolean(args[1]) + " " + args[2] + " " + Arrays.toString(loadFile(args[3])) + " " + args[4]);
				final String rid = getModule().createPrescription(new Boolean(args[1]), args[2], loadFile(args[3]), args[4]);
				OUT.println("Prescription successfully created : " + rid);

			} else if (args.length == 6 && args[0].equals("createPrescriptionsSequentially")) {
				final Date testStart = Calendar.getInstance().getTime();

				final int nrOfPrescriptions = Integer.parseInt(args[5]);
				final List<String> createdPrescriptions = new LinkedList<String>();

				int i = 0;
				for (; i < nrOfPrescriptions; i++) {
					OUT.println("Creating prescription with index " + i);

					final String rid = getModule().createPrescription(new Boolean(args[1]), args[2], loadFile(args[3]), args[4]);
					OUT.println("Prescription " + i + "  successfully created : " + rid);
					createdPrescriptions.add(rid);
				}

				final Date testEnd = Calendar.getInstance().getTime();
				System.out.println((i - 1) + " precriptions were created between " + testStart + " until " + testEnd);
				OUT.println("Summary of created prescriptions: ");
				for (final String rid : createdPrescriptions) {
					OUT.println("\b" + rid);
				}

			} else if (args.length == 2 && args[0].equals("getPrescription")) {

				final GetPrescriptionForPrescriberResult p = getModule().getPrescription(args[1]);

				OUT.println("Creation Date : " + p.getCreationDate().getTime());
				OUT.println("Patient : " + p.getPatientId());
				OUT.println("FeedbackAllowed : " + p.isFeedbackAllowed());
				OUT.println("Prescription :\n" + new String(p.getPrescription()));

			} else if (args.length == 2 && args[0].equals("listFeedback")) {
				final List<ListFeedbackItem> fbs = getModule().listFeedback(true);
				int i = 0;
				for (final ListFeedbackItem it : fbs) {
					OUT.println("--------------------------------------------------");
					OUT.println("Feedback : " + (++i));
					OUT.println("Sent by : " + it.getSentBy());
					OUT.print(it.getSentDate());
					OUT.println("Sent Date : " + (it.getSentDate() != null ? it.getSentDate().getTime() : null));
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

				getModule().sendNotification(loadFile(args[1]), args[2], args[3]);

				OUT.println("Notification sent!");

			} else if (args.length == 5 && args[0].equals("sendNotificationsSequentially")) {

				final int nrOfNotifications = Integer.parseInt(args[4]);

				int i = 0;
				for (; i < nrOfNotifications; i++) {
					OUT.println("Creating notification with index " + i);

					getModule().sendNotification(loadFile(args[1]), args[2], args[3]);
					OUT.println("Notification sent!");
				}

				System.out.println(i + "notifications were created between ");

			} else if (args.length == 3 && args[0].equals("updateFeedbackFlag")) {

				getModule().updateFeedbackFlag(args[1], Boolean.getBoolean(args[2]));

				OUT.println("Feedback flag updated!");

			} else if (args.length == 3 && args[0].equals("listOpenRids")) {
				OUT.println("ListOpenRids called - for more information look into the log file. ");
				final ListOpenRidsParam param = new ListOpenRidsParam();
				param.setPatientId(args[1]);
				final Page page = new Page();
				page.setPageNumber(java.math.BigInteger.valueOf(Integer.parseInt(args[2])));
				param.setPage(page);
				final ListOpenRidsResult p = ((PrescriberIntegrationModuleV4Impl) getModule()).getData(param);
				printListOpenRidsResult(args[1], p);
				OUT.println("ListPrescriptionHistory call ended. You can close the window.");
			} else if (args.length == 1 && args[0].equals("prompt")) {

				String command = null;
				final InputStreamReader converter = new InputStreamReader(System.in);
				final BufferedReader in = new BufferedReader(converter);
				System.out.print("Prompt mode started. Input your command:\n$");
				while ((command = in.readLine()) != null) {
					if ("q".equalsIgnoreCase(command) || "quit".equalsIgnoreCase(command)) {
						System.exit(0);
					}
					main(command.split(" "));
					System.out.print("\n$");
				}

			} else if (args.length == 3 && args[0].equals("listPrescriptionHistory")) {
				OUT.println("ListPrescriptionHistory called - for more information look into the log file. ");
				final ListRidsHistoryParam param = new ListRidsHistoryParam();
				param.setPatientId(args[1]);
				final Page page = new Page();
				page.setPageNumber(java.math.BigInteger.valueOf(Integer.parseInt(args[2])));
				param.setPage(page);
				final ListRidsHistoryResult p = ((PrescriberIntegrationModuleV4Impl) getModule()).getData(param);
				printListPrescriptionHistoryResult(args[1], p);
				OUT.println("ListPrescriptionHistory call ended. You can close the window.");
			} else if (args.length == 3 && args[0].equals("putVision")) {
				OUT.println("putVision called - for more information look into the log file. ");
				final PutVisionParam param = new PutVisionParam();
				param.setRid(args[1]);
				param.setVision(args[2]);
				((PrescriberIntegrationModuleV4Impl) getModule()).putData(param);
				OUT.println("putVision call ended. You can close the window.");
			}else {
				if (args.length > 0) {
					OUT.println("ERROR : Invalid number of arguments");
				}
				showHelp();
			}

		} catch (final Throwable t) {
			t.printStackTrace();
		}
	}

	private static PrescriberIntegrationModuleV4 initModule() throws Exception {
		initializeSystem();
		return new PrescriberIntegrationModuleV4Impl();
	}

	/**
	 * Lazy getter for the module.
	 *
	 * @return the module
	 * @throws IntegrationModuleException
	 *             the integration module exception
	 */
	private static PrescriberIntegrationModuleV4 getModule() throws Exception {
		if (module == null) {
			module = initModule();
		}
		return module;
	}

	/**
	 * Show help.
	 */
	private static void showHelp() {
		final StringBuffer sb = new StringBuffer();

		OUT.println("Usage :  java <classpath> <-Doption1=option2> " + PrescriberIntegrationModuleCommandLine.class.getName()
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

		OUT.println(" ListPrescriptionHistory <arg1> <arg2> <arg3>");
		OUT.println("	  arg1 : NISS");
		OUT.println("	  arg2 : page");
		OUT.println("  ex : listPrescriptionHistory 76020727360 0");
		OUT.println("");

		OUT.println(" PutVision <arg1> <arg2>");
		OUT.println("	  arg1 : RID");
		OUT.println("	  arg2 : vision");
		OUT.println("  ex : putVision BEPPabcdefgh locked");
		OUT.println("");

		OUT.println("-------------- OPTIONS ------------");
		OUT.println(" -Dconfig=<path to the config file> : specify a config file");
		OUT.println(" -Dsession=<path to Xml session file> : specify a session");
		OUT.println(" -DsystemKeystore=<path to system keystore properties file> : specify the system keystore properties");

		OUT.println(sb.toString());
	}

	private static void printListPrescriptionHistoryResult(final String patientId, final ListRidsHistoryResult p) {
		OUT.println("GetOpenPrescriptionList for " + patientId);
		for (final ListRidsHistoryResultItem item : p.getItems()) {
			OUT.println("Prescription: rid : " + item.getRid() + " Status: " + item.getPrescriptionStatus());
		}
	}

	/**
	 * @param string
	 * @param p
	 */
	private static void printListOpenRidsResult(String patientId, ListOpenRidsResult p) {
		OUT.println("GetOpenPrescriptionList for " + patientId);
		for (final String rid : p.getPrescriptions()) {
			OUT.println("Prescription: rid : " + rid);
		}
	}

}
