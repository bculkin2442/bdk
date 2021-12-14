
package sunw.demo.molecule;

/**
 * Special case property editor for molecule names.
 */

public class MoleculeNameEditor extends java.beans.PropertyEditorSupport
{

	@Override
	public String[] getTags() {
		String result[] =
		{
				"HyaluronicAcid", "benzene", "buckminsterfullerine", "cyclohexane",
				"ethane", "water"
		};
		return result;
	}

	@Override
	public String getJavaInitializationString() {
		return (String) getValue();
	}

}
