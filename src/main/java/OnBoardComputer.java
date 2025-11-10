public class OnBoardComputer implements BurnStream {

    @Override
    public int getNextBurn(DescentEvent status) {
        int altitude = status.getAltitude();
        int velocity = status.getVelocity();
        
        if (altitude < 100) {
            
            if (velocity > 50) {
                return 200; 
            } else if (velocity > 10) {
                return 150; 
            } else if (velocity > 2) {
                return 125; 
            } else {
                return 100; 
            }
        }
        
        
        int burn;
        double targetVelocity;
        
        if (altitude > 5000) {
            targetVelocity = 300; 
        } else if (altitude > 2000) {
            targetVelocity = 200; 
        } else if (altitude > 1000) {
            targetVelocity = 100; 
        } else {
            targetVelocity = 50; 
        }
        
        
        if (velocity > targetVelocity + 100) {
            burn = 200; 
        } else if (velocity > targetVelocity) {
            burn = 150; 
        } else if (velocity < targetVelocity - 100) {
            burn = 50; 
        } else {
            burn = 100; 
        }
        
        System.out.println(burn);
        return burn;
    }

}
