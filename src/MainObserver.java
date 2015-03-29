import java.util.Observable;
import java.util.Observer;


public class MainObserver implements Observer //will extend JFrame when it comes to GUI
{
	private MemoryManagerModel model;
	@Override
	public void update(Observable arg0, Object arg1)
	{
		//Will be called on allocate() or deallocate()
		//We will loop through the model's memoryBlocks and re-draw the screen
		
	}
	public MainObserver(MemoryManagerModel model)
	{
		this.model = model;
		model.addObserver(this);
	}
}
