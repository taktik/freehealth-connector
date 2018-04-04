package org.taktik.freehealth.middleware.drugs.logic;

/**
 * Created by bernardpaulus on 4/04/17.
 */
public class DrugsDatabaseConnectionFailedException extends RuntimeException {
    private static final long serialVersionUID = 0x3d395c9141000c5cL;

    public DrugsDatabaseConnectionFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
