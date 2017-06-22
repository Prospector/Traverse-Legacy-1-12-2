package prospector.traverse.core;

import prospector.shootingstar.version.Version;

public class TraverseConstants {
    public static final String MOD_NAME = "TraverseMod";
    public static final String MOD_ID = "traverse";
    public static final String MOD_VERSION_MAJOR = "@major@";
    public static final String MOD_VERSION_MINOR = "@minor@";
    public static final String MOD_VERSION_PATCH = "@patch@";
    public static final Version DEV_VERSION = new Version(999, 0, 0);
    public static final String MINECRAFT_VERSIONS = "@mcversion@";
    public static final String CLIENT_PROXY_CLASS = "prospector.traverse.TraverseClient";
    public static final String SERVER_PROXY_CLASS = "prospector.traverse.TraverseServer";
}
