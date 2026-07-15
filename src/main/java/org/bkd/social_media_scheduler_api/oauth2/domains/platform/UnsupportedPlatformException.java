package org.bkd.social_media_scheduler_api.oauth2.domains.platform;

public class UnsupportedPlatformException extends Exception {

  public UnsupportedPlatformException(Platform platform) {
    super("Unsupported platform: " + platform);
  }
}
