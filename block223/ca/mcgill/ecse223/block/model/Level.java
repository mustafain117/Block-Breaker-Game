/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

// line 45 "../../../../../umple.ump"
public class Level
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Level Attributes
  private int levelNumber;
  private boolean isRandom;

  //Level Associations
  private Admin admin;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Level(int aLevelNumber, boolean aIsRandom, Admin aAdmin)
  {
    levelNumber = aLevelNumber;
    isRandom = aIsRandom;
    boolean didAddAdmin = setAdmin(aAdmin);
    if (!didAddAdmin)
    {
      throw new RuntimeException("Unable to create level due to admin");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setLevelNumber(int aLevelNumber)
  {
    boolean wasSet = false;
    levelNumber = aLevelNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsRandom(boolean aIsRandom)
  {
    boolean wasSet = false;
    isRandom = aIsRandom;
    wasSet = true;
    return wasSet;
  }

  public int getLevelNumber()
  {
    return levelNumber;
  }

  public boolean getIsRandom()
  {
    return isRandom;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isIsRandom()
  {
    return isRandom;
  }
  /* Code from template association_GetOne */
  public Admin getAdmin()
  {
    return admin;
  }
  /* Code from template association_SetOneToAtMostN */
  public boolean setAdmin(Admin aAdmin)
  {
    boolean wasSet = false;
    //Must provide admin to level
    if (aAdmin == null)
    {
      return wasSet;
    }

    //admin already at maximum (99)
    if (aAdmin.numberOfLevels() >= Admin.maximumNumberOfLevels())
    {
      return wasSet;
    }
    
    Admin existingAdmin = admin;
    admin = aAdmin;
    if (existingAdmin != null && !existingAdmin.equals(aAdmin))
    {
      boolean didRemove = existingAdmin.removeLevel(this);
      if (!didRemove)
      {
        admin = existingAdmin;
        return wasSet;
      }
    }
    admin.addLevel(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Admin placeholderAdmin = admin;
    this.admin = null;
    if(placeholderAdmin != null)
    {
      placeholderAdmin.removeLevel(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "levelNumber" + ":" + getLevelNumber()+ "," +
            "isRandom" + ":" + getIsRandom()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "admin = "+(getAdmin()!=null?Integer.toHexString(System.identityHashCode(getAdmin())):"null");
  }
}