package act.sys.meta;

import act.asm.Type;
import act.util.DestroyableBase;

/**
 * Keep track Class meta info
 */
public class ClassMetaInfo extends DestroyableBase {
    /**
     * The ASM type of this class
     */
    private Type type;

    /**
     * The ASM type of the super class. The `superType`
     * is populated by a bytecode scanner and will then
     * used to get the {@link #superInfo} from the
     * MetaInfoRegistry
     *
     * **Note** only controller need this in order to
     * look up the URL context path from the parent
     * class.
     */
    private Type superType;

    /**
     * The `ClassMetaInfo` of the {@link #superType}
     */
    private ClassMetaInfo superInfo;
}
