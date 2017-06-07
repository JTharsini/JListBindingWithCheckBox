public class RecipientWrapper
{
  private String s;

  private boolean selected;

  /**
   * @param s
   */
  public RecipientWrapper(String s)
  {
    this.s = s;
  }

  public String getString()
  {
    return s;
  }

  public boolean isSelected()
  {
    return selected;
  }

  public void setSelected(boolean selected)
  {
    this.selected = selected;
  }
}