public class BurnDataStream implements BurnStream {

    int burnArray[] = {100, 100, 200, 200, 100, 100, 0, 0, 200, 100, 100, 0, 0, 0, 0};
    int burnIdx = -1;

    public BurnDataStream() { }
    public BurnDataStream(int[] burns) {
        this.burnArray = burns;
    }
    @Override
    public int getNextBurn(DescentEvent status) {
        burnIdx++;
        if (burnIdx < burnArray.length) {
            System.out.println(burnArray[burnIdx]); 
            return burnArray[burnIdx];
        }
        return 0;
    }
}
