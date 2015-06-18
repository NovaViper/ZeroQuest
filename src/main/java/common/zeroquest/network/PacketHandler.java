package common.zeroquest.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import common.zeroquest.lib.Constants;
import common.zeroquest.network.imessage.*;

/**
 *
 * This class will house the SimpleNetworkWrapper instance, which I will name
 * 'dispatcher', as well as give us a logical place from which to register our
 * packets. These two things could be done anywhere, however, even in your Main
 * class, but I will be adding other functionality (see below) that gives this
 * class a bit more utility.
 *
 * While unnecessary, I'm going to turn this class into a 'wrapper' for
 * SimpleNetworkWrapper so that instead of writing
 * "PacketDispatcher.dispatcher.{method}" I can simply write
 * "PacketDispatcher.{method}" All this does is make it quicker to type and
 * slightly shorter; if you do not care about that, then make the 'dispatcher'
 * field public instead of private, or, if you do not want to add a new class
 * just for one field and one static method that you could put anywhere, feel
 * free to put them wherever.
 *
 * For further convenience, I have also added two extra sendToAllAround methods:
 * one which takes an EntityPlayer and one which takes coordinates.
 *
 */
public class PacketHandler {
	// a simple counter will allow us to get rid of 'magic' numbers used during
	// packet registration
	private static byte packetId = 0;

	/**
	 * The SimpleNetworkWrapper instance is used both to register and send
	 * packets. Since I will be adding wrapper methods, this field is private,
	 * but you should make it public if you plan on using it directly.
	 */
	private static final SimpleNetworkWrapper dispatcher = NetworkRegistry.INSTANCE.newSimpleChannel(Constants.channel);

	/**
	 * Call this during pre-init or loading and register all of your packets
	 * (messages) here
	 */
	public static final void registerPackets() {
		// Using an incrementing field instead of hard-coded numerals, I don't
		// need to think
		// about what number comes next or if I missed on should I ever
		// rearrange the order
		// of registration (for instance, if you wanted to alphabetize them...
		// yeah...)
		// It's even easier if you create a convenient 'registerMessage' method:
		registerMessage(ZertumJump.Handler.class, ZertumJump.class, Side.SERVER);
		registerMessage(JakanJump.Handler.class, JakanJump.class, Side.SERVER);
		registerMessage(KortorJump.Handler.class, KortorJump.class, Side.SERVER);
		registerMessage(SealCommand.Handler.class, SealCommand.class, Side.SERVER);
		registerMessage(ZertumObey.Handler.class, ZertumObey.class, Side.SERVER);
		registerMessage(ZertumName.Handler.class, ZertumName.class, Side.SERVER);
		registerMessage(ZertumMode.Handler.class, ZertumMode.class, Side.SERVER);
		registerMessage(ZertumTalents.Handler.class, ZertumTalents.class, Side.SERVER);
		registerMessage(FireSound.Handler.class, FireSound.class, Side.SERVER);
		//registerMessage(ZertumBoundingBox.Handler.class, ZertumBoundingBox.class, Side.CLIENT);
	}

	/**
	 * Registers a message and message handler
	 */
	private static final void registerMessage(Class handlerClass, Class messageClass, Side side) {
		PacketHandler.dispatcher.registerMessage(handlerClass, messageClass, packetId++, side);
	}

	// ========================================================//
	// The following methods are the 'wrapper' methods; again,
	// this just makes sending a message slightly more compact
	// and is purely a matter of stylistic preference
	// ========================================================//

	/**
	 * Send this message to the specified player. See
	 * {@link SimpleNetworkWrapper#sendTo(IMessage, EntityPlayerMP)}
	 */
	public static final void sendTo(IMessage message, EntityPlayerMP player) {
		PacketHandler.dispatcher.sendTo(message, player);
	}

	/**
	 * Send this message to everyone within a certain range of a point. See
	 * {@link SimpleNetworkWrapper#sendToDimension(IMessage, NetworkRegistry.TargetPoint)}
	 */
	public static final void sendToAllAround(IMessage message, NetworkRegistry.TargetPoint point) {
		PacketHandler.dispatcher.sendToAllAround(message, point);
	}

	/**
	 * Sends a message to everyone within a certain range of the coordinates in
	 * the same dimension.
	 */
	public static final void sendToAllAround(IMessage message, int dimension, double x, double y, double z, double range) {
		PacketHandler.sendToAllAround(message, new NetworkRegistry.TargetPoint(dimension, x, y, z, range));
	}

	/**
	 * Sends a message to everyone within a certain range of the player
	 * provided.
	 */
	public static final void sendToAllAround(IMessage message, EntityPlayer player, double range) {
		PacketHandler.sendToAllAround(message, player.worldObj.provider.getDimensionId(), player.posX,

				player.posY, player.posZ, range);
	}

	/**
	 * Send this message to everyone within the supplied dimension. See
	 * {@link SimpleNetworkWrapper#sendToDimension(IMessage, int)}
	 */
	public static final void sendToDimension(IMessage message, int dimensionId) {
		PacketHandler.dispatcher.sendToDimension(message, dimensionId);
	}

	/**
	 * Send this message to the server. See
	 * {@link SimpleNetworkWrapper#sendToServer(IMessage)}
	 */
	public static final void sendToServer(IMessage message) {
		PacketHandler.dispatcher.sendToServer(message);
	}
}