/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * aapt tool from the resource data it found.  It
 * should not be modified by hand.
 */

package cs275.game.mazeCraze;

public final class R {
    public static final class attr {
        /** <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
         */
        public static final int buttonBarButtonStyle=0x7f010001;
        /** <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
         */
        public static final int buttonBarStyle=0x7f010000;
    }
    public static final class color {
        public static final int black_overlay=0x7f050000;
    }
    public static final class drawable {
        public static final int clouds_large=0x7f020000;
        public static final int dirt=0x7f020001;
        public static final int hedge=0x7f020002;
        public static final int ic_launcher=0x7f020003;
        public static final int red_brick=0x7f020004;
        public static final int tile=0x7f020005;
    }
    public static final class id {
        public static final int RelativeLayout1=0x7f080005;
        public static final int btncreate=0x7f080015;
        public static final int btngenerate=0x7f080002;
        public static final int btnload=0x7f080018;
        public static final int btnrandom=0x7f080017;
        public static final int btnreturn=0x7f080003;
        public static final int btnsave=0x7f080000;
        public static final int btnstartcreate=0x7f080012;
        public static final int imglogo=0x7f080016;
        public static final int laycreator=0x7f080004;
        public static final int laystyle=0x7f080006;
        public static final int lblalgoritm=0x7f080019;
        public static final int lblfloor=0x7f08000a;
        public static final int lbloptions=0x7f08000c;
        public static final int lblsize=0x7f08000e;
        public static final int lblsizedelimiter=0x7f080010;
        public static final int lblstyle=0x7f080007;
        public static final int lblwalls=0x7f080008;
        public static final int lstResults=0x7f080014;
        public static final int mazeCreationInstructions=0x7f080001;
        public static final int spinalgorithm=0x7f08001a;
        public static final int spinfloor=0x7f08000b;
        public static final int spinwall=0x7f080009;
        public static final int txtMazeCreator=0x7f080013;
        public static final int txtgridx=0x7f08000f;
        public static final int txtgridy=0x7f080011;
        public static final int txtname=0x7f08000d;
    }
    public static final class layout {
        public static final int creator_menu=0x7f030000;
        public static final int creator_options_menu=0x7f030001;
        public static final int load_menu=0x7f030002;
        public static final int main_menu=0x7f030003;
        public static final int random_menu=0x7f030004;
    }
    public static final class raw {
        public static final int fragmentshader=0x7f040000;
        public static final int vertexshader=0x7f040001;
    }
    public static final class string {
        public static final int app_name=0x7f060000;
        public static final int desclogo=0x7f06000d;
        public static final int hintcreator=0x7f060005;
        public static final int hintname=0x7f060004;
        public static final int lblalgorithm=0x7f060012;
        public static final int lblcreate=0x7f060010;
        public static final int lblcreated=0x7f06000f;
        public static final int lbldescription=0x7f060006;
        public static final int lblfloors=0x7f06000a;
        public static final int lblgenerate=0x7f060011;
        public static final int lblinstructions=0x7f060003;
        public static final int lbloptions=0x7f060007;
        public static final int lblrandom=0x7f06000e;
        public static final int lblreturn=0x7f060002;
        public static final int lblsave=0x7f060001;
        public static final int lblsize=0x7f06000b;
        public static final int lblsizedelimiter=0x7f06000c;
        public static final int lblstyle=0x7f060008;
        public static final int lblwalls=0x7f060009;
    }
    public static final class style {
        /** 
        Base application theme, dependent on API level. This theme is replaced
        by AppBaseTheme from res/values-vXX/styles.xml on newer devices.


    

            Theme customizations available in newer API levels can go in
            res/values-vXX/styles.xml, while customizations related to
            backward-compatibility can go here.


        
         */
        public static final int AppBaseTheme=0x7f070000;
        /**  Application theme. 
         */
        public static final int AppTheme=0x7f070001;
    }
    public static final class styleable {
        /** 
         Declare custom theme attributes that allow changing which styles are
         used for button bars depending on the API level.
         ?android:attr/buttonBarStyle is new as of API 11 so this is
         necessary to support previous API levels.
    
           <p>Includes the following attributes:</p>
           <table>
           <colgroup align="left" />
           <colgroup align="left" />
           <tr><th>Attribute</th><th>Description</th></tr>
           <tr><td><code>{@link #ButtonBarContainerTheme_buttonBarButtonStyle cs275.game.mazeCraze:buttonBarButtonStyle}</code></td><td></td></tr>
           <tr><td><code>{@link #ButtonBarContainerTheme_buttonBarStyle cs275.game.mazeCraze:buttonBarStyle}</code></td><td></td></tr>
           </table>
           @see #ButtonBarContainerTheme_buttonBarButtonStyle
           @see #ButtonBarContainerTheme_buttonBarStyle
         */
        public static final int[] ButtonBarContainerTheme = {
            0x7f010000, 0x7f010001
        };
        /**
          <p>This symbol is the offset where the {@link cs275.game.mazeCraze.R.attr#buttonBarButtonStyle}
          attribute's value can be found in the {@link #ButtonBarContainerTheme} array.


          <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
          @attr name cs275.game.mazeCraze:buttonBarButtonStyle
        */
        public static final int ButtonBarContainerTheme_buttonBarButtonStyle = 1;
        /**
          <p>This symbol is the offset where the {@link cs275.game.mazeCraze.R.attr#buttonBarStyle}
          attribute's value can be found in the {@link #ButtonBarContainerTheme} array.


          <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
          @attr name cs275.game.mazeCraze:buttonBarStyle
        */
        public static final int ButtonBarContainerTheme_buttonBarStyle = 0;
    };
}
