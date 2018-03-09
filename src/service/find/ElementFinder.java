package service.find;

public interface ElementFinder {
    String findElementPathBasedOnLinkTag(String sourceFile, String sourceElementId, String targetFile);
}
