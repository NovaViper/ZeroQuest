package common.zeroquest.fluid;

import net.minecraftforge.fluids.Fluid;

public class AcidFluid extends Fluid{

	public AcidFluid(String fluidName) {
		super(fluidName);
		this.setLuminosity(0);
		this.setDensity(2000);
		this.setViscosity(1500);
		this.setTemperature(400);
		this.setGaseous(false);
	}
	
	/**DEFAULT VALUES
	 * Lumionsity: 0
	 * Density: 1000
	 * Viscosity: 1000
	 * Temperature: 295
	 * Gaseous: false
	**/

}
