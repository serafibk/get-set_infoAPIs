package getInfo;

/* To be imported on VRMilling2
import javafish.clients.opc.component.OpcGroup;
import javafish.clients.opc.component.OpcItem;
import javafish.clients.opc.exception.CoInitializeException;
import javafish.clients.opc.exception.CoUninitializeException;
import javafish.clients.opc.exception.ComponentNotFoundException;
import javafish.clients.opc.exception.ConnectivityException;
import javafish.clients.opc.exception.SynchReadException;
import javafish.clients.opc.exception.SynchWriteException;
import javafish.clients.opc.exception.UnableAddGroupException;
import javafish.clients.opc.exception.UnableAddItemException;
import javafish.clients.opc.exception.UnableRemoveGroupException;
import javafish.clients.opc.exception.UnableRemoveItemException;
import javafish.clients.opc.variant.Variant;
import javafish.clients.opc.variant.VariantList;
 */
public class getExistingGroups {

	JOpc jopc = new JOpc("localhost", "RSLinx OPC Server", "JOPC1");
    
    try {
      JOpc.coInitialize();
    }
    catch (CoInitializeException e1) {
      e1.printStackTrace();
    }
}
