/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

// line 10 "../../../../../umple.ump"
public abstract class UserRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //UserRole Associations
  private User user;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public UserRole(User aUser)
  {
    boolean didAddUser = setUser(aUser);
    if (!didAddUser)
    {
      throw new RuntimeException("Unable to create userRole due to user");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public User getUser()
  {
    return user;
  }
  /* Code from template association_SetOneToAtMostN */
  public boolean setUser(User aUser)
  {
    boolean wasSet = false;
    //Must provide user to userRole
    if (aUser == null)
    {
      return wasSet;
    }

    //user already at maximum (2)
    if (aUser.numberOfUserRoles() >= User.maximumNumberOfUserRoles())
    {
      return wasSet;
    }
    
    User existingUser = user;
    user = aUser;
    if (existingUser != null && !existingUser.equals(aUser))
    {
      boolean didRemove = existingUser.removeUserRole(this);
      if (!didRemove)
      {
        user = existingUser;
        return wasSet;
      }
    }
    user.addUserRole(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    User placeholderUser = user;
    this.user = null;
    if(placeholderUser != null)
    {
      placeholderUser.removeUserRole(this);
    }
  }

}