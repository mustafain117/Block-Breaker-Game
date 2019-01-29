/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.util.*;

// line 51 "../../../../../umple.ump"
public class Block
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  public static final int SideSize = 20;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Block Attributes
  private String color;
  private int pointValue;

  //Block Associations
  private List<BlockArrangement> blockArrangements;
  private Admin admin;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Block(String aColor, int aPointValue, Admin aAdmin)
  {
    color = aColor;
    pointValue = aPointValue;
    blockArrangements = new ArrayList<BlockArrangement>();
    boolean didAddAdmin = setAdmin(aAdmin);
    if (!didAddAdmin)
    {
      throw new RuntimeException("Unable to create block due to admin");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setColor(String aColor)
  {
    boolean wasSet = false;
    color = aColor;
    wasSet = true;
    return wasSet;
  }

  public boolean setPointValue(int aPointValue)
  {
    boolean wasSet = false;
    pointValue = aPointValue;
    wasSet = true;
    return wasSet;
  }

  public String getColor()
  {
    return color;
  }

  public int getPointValue()
  {
    return pointValue;
  }
  /* Code from template association_GetMany */
  public BlockArrangement getBlockArrangement(int index)
  {
    BlockArrangement aBlockArrangement = blockArrangements.get(index);
    return aBlockArrangement;
  }

  public List<BlockArrangement> getBlockArrangements()
  {
    List<BlockArrangement> newBlockArrangements = Collections.unmodifiableList(blockArrangements);
    return newBlockArrangements;
  }

  public int numberOfBlockArrangements()
  {
    int number = blockArrangements.size();
    return number;
  }

  public boolean hasBlockArrangements()
  {
    boolean has = blockArrangements.size() > 0;
    return has;
  }

  public int indexOfBlockArrangement(BlockArrangement aBlockArrangement)
  {
    int index = blockArrangements.indexOf(aBlockArrangement);
    return index;
  }
  /* Code from template association_GetOne */
  public Admin getAdmin()
  {
    return admin;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlockArrangements()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addBlockArrangement(BlockArrangement aBlockArrangement)
  {
    boolean wasAdded = false;
    if (blockArrangements.contains(aBlockArrangement)) { return false; }
    blockArrangements.add(aBlockArrangement);
    if (aBlockArrangement.indexOfBlock(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aBlockArrangement.addBlock(this);
      if (!wasAdded)
      {
        blockArrangements.remove(aBlockArrangement);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeBlockArrangement(BlockArrangement aBlockArrangement)
  {
    boolean wasRemoved = false;
    if (!blockArrangements.contains(aBlockArrangement))
    {
      return wasRemoved;
    }

    int oldIndex = blockArrangements.indexOf(aBlockArrangement);
    blockArrangements.remove(oldIndex);
    if (aBlockArrangement.indexOfBlock(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aBlockArrangement.removeBlock(this);
      if (!wasRemoved)
      {
        blockArrangements.add(oldIndex,aBlockArrangement);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBlockArrangementAt(BlockArrangement aBlockArrangement, int index)
  {  
    boolean wasAdded = false;
    if(addBlockArrangement(aBlockArrangement))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlockArrangements()) { index = numberOfBlockArrangements() - 1; }
      blockArrangements.remove(aBlockArrangement);
      blockArrangements.add(index, aBlockArrangement);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBlockArrangementAt(BlockArrangement aBlockArrangement, int index)
  {
    boolean wasAdded = false;
    if(blockArrangements.contains(aBlockArrangement))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlockArrangements()) { index = numberOfBlockArrangements() - 1; }
      blockArrangements.remove(aBlockArrangement);
      blockArrangements.add(index, aBlockArrangement);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBlockArrangementAt(aBlockArrangement, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setAdmin(Admin aAdmin)
  {
    boolean wasSet = false;
    if (aAdmin == null)
    {
      return wasSet;
    }

    Admin existingAdmin = admin;
    admin = aAdmin;
    if (existingAdmin != null && !existingAdmin.equals(aAdmin))
    {
      existingAdmin.removeBlock(this);
    }
    admin.addBlock(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<BlockArrangement> copyOfBlockArrangements = new ArrayList<BlockArrangement>(blockArrangements);
    blockArrangements.clear();
    for(BlockArrangement aBlockArrangement : copyOfBlockArrangements)
    {
      aBlockArrangement.removeBlock(this);
    }
    Admin placeholderAdmin = admin;
    this.admin = null;
    if(placeholderAdmin != null)
    {
      placeholderAdmin.removeBlock(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "color" + ":" + getColor()+ "," +
            "pointValue" + ":" + getPointValue()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "admin = "+(getAdmin()!=null?Integer.toHexString(System.identityHashCode(getAdmin())):"null");
  }
}