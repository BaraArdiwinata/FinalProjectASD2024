public class Pusaka {
    private String pathChoice;
    private String description;
    private Potter potter;


    public Pusaka(String pathChoice, Potter potter){
        this.pathChoice = pathChoice;
        this.potter = potter;

    }

    private void LightField(){
        description = "By using this weapon, you can find the nearest route to fight monsters.";
        //Write Algorithm

    }
    private void FaithFortress(){
        description = "By using this weapon, you can find the most possible route to get the most coins to fight monsters.";
    }
    private void FatesDawnTower(){
        description = "By using this weapon, you can find the most possible route to get the most energy to fight monsters.";
    }
}
