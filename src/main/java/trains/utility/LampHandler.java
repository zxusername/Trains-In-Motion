package trains.utility;

import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import trains.blocks.LampBlock;
import trains.registry.BlockRegistry;

public class LampHandler {
    public int X;
    public int Y;
    public int Z;
    public boolean isOn;

    /**
     * used to check if the lamp needs a position update.
     * We extend this into its own class so we can better manage the variables.
     * this us used by
     * @see trains.entities.MinecartExtended
     *
     * @param worldObj the world to place the lamp.
     * @param x the X position to place the lamp at.
     * @param y the Y position to place the lamp at.
     * @param z the Z position to place the lamp at.
     */
    public void ShouldUpdate(World worldObj, double x, double y, double z){
        if(isOn && (X !=MathHelper.floor_double(x) ^ Y != MathHelper.floor_double(y) ^ Z != MathHelper.floor_double(z))){
            //if there was a block placed previously, remove it.
            if (X != 0 && Y != 0 && Z != 0) {
                worldObj.setBlockToAir(X, Y, Z);
            }
            //set the values
            X = MathHelper.floor_double(x);
            Y = MathHelper.floor_double(y);
            Z = MathHelper.floor_double(z);
            //create the block.
            if (!(worldObj.getBlock(X,Y,Z) instanceof LampBlock)) {
                worldObj.setBlock(X,Y,Z, BlockRegistry.lampBlock);
                worldObj.getBlock(X,Y,Z).setLightLevel(1f);
            }

        }
    }


}