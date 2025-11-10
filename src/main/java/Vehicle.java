public class Vehicle {

    public Vehicle(int InitialAltitude) {
        this.Altitude = InitialAltitude;
        this.PrevAltitude = InitialAltitude;
        this.Velocity = 0;
        this.Fuel = 12000;
        this.Burn = 0;
        this.Flying = FLYING;
    }

    int Gravity = 100;
    /* The rate in which the spaceship descents in free fall (in ten seconds) */

    // Various end-of-game messages and status result codes.
    String dead = "\nCRASH!!\n\tThere were no survivors.\n\n";
    public static final int DEAD = -3;
    String crashed = "\nThe Starship crashed. Good luck getting back home. Elon is pissed.\n\n";
    public static final int CRASHED = -2;
    String emptyfuel = "\nThere is no fuel left. You're floating around like Major Tom.\n\n";
    public static final int EMPTYFUEL = -1;
    String success = "\nYou made it! Good job!\n\n";
    public static final int SUCCESS = 0;
    public static final int FLYING = 1;

    // this is initial vehicle setup
    int Altitude= 8000;
    int PrevAltitude= 8000;

    int Velocity= 1000;
    int Fuel = 12000;
    int Burn = 0;
    int Flying = FLYING;

    public Vehicle() {}

    public String checkFinalStatus() {
        if (this.Fuel <= 0) {
            Flying = EMPTYFUEL;
            return emptyfuel;
        } else if (this.Altitude <= 0) {
            if (this.Velocity >= 3) {
                Flying = CRASHED;
                return crashed;
            } else {
                Flying = SUCCESS;
                return success;
            }
        }
        Flying = FLYING;
        return "";
    }

    public int computeDeltaV() {
        return Velocity + Gravity - Burn;
    }

    public void adjustForBurn(int burnAmount) {
        Burn = burnAmount;
        PrevAltitude = Altitude;
        Velocity = computeDeltaV();
        Altitude -= Velocity;
        Fuel -= burnAmount;
    }

    public boolean stillFlying() {
        return Flying == FLYING && Altitude > 0 && Fuel > 0;
    }

    public boolean outOfFuel() {
        if (Fuel <= 0) {
            Flying = EMPTYFUEL;
            return true;
        }
        return false;
    }

    public DescentEvent getStatus(int tick) {
        if (Fuel <= 0) {
            Flying = EMPTYFUEL;
        } else if (Altitude <= 0) {
            Flying = (Velocity >= 3) ? CRASHED : SUCCESS;
        }
        return new DescentEvent(tick, Velocity, Fuel, Altitude, Flying);
    }

}
