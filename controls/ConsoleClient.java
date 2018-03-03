/**
 * 
 */
package controls;

import gamecore.Intel;

/**
 * This is just simply to Test. Inputs will be done with console and debugging outputs.
 * 
 * @author Andreas Stock
 *
 */
public class ConsoleClient extends Client {

	public ConsoleClient(int id) {
		super(id);
	}

	@Override
	public void post(Intel intel) {
		// TODO Auto-generated method stub
		System.out.println("IntelGotten: \n"+intel.toString());
	}

}
