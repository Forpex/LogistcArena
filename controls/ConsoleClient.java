/**
 * 
 */
package controls;

import gamecore.intel.Intel;

/**
 * This is just simply to Test. Inputs will be done with console and debugging outputs.
 * 
 * @author Andreas Stock
 *
 */
public class ConsoleClient extends Client {

	public ConsoleClient(String name, int id) {
		super(name, id);
	}

	@Override
	public void post(Intel intel) {
		// TODO Auto-generated method stub
		System.out.println("IntelGotten: \n"+intel.toString());
	}

}
