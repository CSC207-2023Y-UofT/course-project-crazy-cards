package ui.windows;



public class StatsWindow implements Window {

    private StatsRenderer renderer;


    public StatsWindow(StatsRenderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void display() {
        // this.renderer.display();
    }

    @Override
    public String getDescription() {
        return "Stats Window";
    }
    
}

