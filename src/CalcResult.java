
public class CalcResult {
	
	public float Result = 0;
	public PerformState State = PerformState.COMPLETE; 
	
	public CalcResult(float result, PerformState state){
		this.Result = result;
		this.State = state;
	}
}
