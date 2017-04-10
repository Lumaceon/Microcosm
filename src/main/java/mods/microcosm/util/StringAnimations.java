package mods.microcosm.util;

public class StringAnimations
{
    public static String cosmicLightScroll(String name)
    {
        String ret = "";
        String temp;
        byte color = (byte) ((System.currentTimeMillis() / 100) % (name.length() + 6));
        for(int i = 0; i < name.length(); i++)
        {
            switch (color)
            {
                case 0:
                case 1:
                    temp = Colors.WHITE;
                    break;
                case 2:
                case 3:
                    temp = Colors.GREY;
                    break;
                case 4:
                case 5:
                    temp = Colors.DARK_GREY;
                    break;
                default:
                    temp = Colors.BLACK;
                    break;
            }
            color = (byte) ((color - 1) % (name.length() + 6));
            ret = ret + temp + name.substring(i, i+1);
        }

        return ret;
    }
}
