package sk.styk.martin.apkanalyzer.model.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sk.styk.martin.apkanalyzer.model.detail.ActivityData;
import sk.styk.martin.apkanalyzer.model.detail.AppDetailData;
import sk.styk.martin.apkanalyzer.model.detail.AppSource;
import sk.styk.martin.apkanalyzer.model.detail.BroadcastReceiverData;
import sk.styk.martin.apkanalyzer.model.detail.CertificateData;
import sk.styk.martin.apkanalyzer.model.detail.ClassPathData;
import sk.styk.martin.apkanalyzer.model.detail.ContentProviderData;
import sk.styk.martin.apkanalyzer.model.detail.FeatureData;
import sk.styk.martin.apkanalyzer.model.detail.FileData;
import sk.styk.martin.apkanalyzer.model.detail.GeneralData;
import sk.styk.martin.apkanalyzer.model.detail.ResourceData;
import sk.styk.martin.apkanalyzer.model.detail.ServiceData;
import sk.styk.martin.apkanalyzer.util.HashCodeHelper;

/**
 * Data sent to server for analysis.
 * <p>
 * Created by Martin Styk on 06.11.2017.
 */
public class ServerSideAppData {

    // ID of device which uploaded this data
    private String androidId;

    // Hash of data structure, can be used to identify two exactly same apps
    private int hash;

    private AppDetailData.AnalysisMode analysisMode;

    // GeneralData
    private String packageName;
    private String applicationName;
    private String versionName;
    private int versionCode;
    private AppSource source;
    private long apkSize;
    private int minSdkVersion;
    private int targetSdkVersion;

    // CertificateData
    private String signAlgorithm;
    private Date startDate;
    private Date endDate;
    private String publicKeyMd5;
    private String certMd5;
    private int serialNumber;
    private String issuerName;
    private String issuerOrganization;
    private String issuerCountry;
    private String subjectName;
    private String subjectOrganization;
    private String subjectCountry;

    // Activities
    private int numberActivities;
    private List<String> activityNames;
    // combined hash of all activities, can be used to check whether activities are same without comparing all of them
    private int activitiesAggregatedHash;


    // Services
    private int numberServices;
    private List<String> serviceNames;
    // combined hash of all services, can be used to check whether services are same without comparing all of them
    private int servicesAggregatedHash;

    // Content Providers
    private int numberContentProviders;
    private List<String> contentProviderNames;
    // combined hash of all content provides
    private int providersAggregatedHash;

    // Broadcast Receivers
    private int numberBroadcastReceivers;
    private List<String> broadcastReceiverNames;
    // combined hash of all broadcast rec
    private int receiversAggregatedHash;

    // Defined permissions
    private int numberDefinedPermissions;
    private List<String> definedPermissions;
    // combined hash of all defined permissions
    private int definedPermAggregatedHash;

    // Used permissions
    private int numberUsedPermissions;
    private List<String> usedPermissions;
    // combined hash of all used permissions
    private int usedPermAggregatedHash;

    // Features
    private int numberFeatures;
    private List<String> featureNames;
    // combined hash of all used permissions
    private int featuresAggregatedHash;

    // FileData
    private String dexHash;
    private String arscHash;
    // hashes of all files in APK
    private List<String> drawableHashes;
    private List<String> layoutHashes;
    private List<String> assetHashes;
    private List<String> otherHashes;

    private int numberDrawables;
    private int numberLayouts;
    private int numberAssets;
    private int numberOthers;

    // single combined hash of all files in category
    private int drawablesAggregatedHash;
    private int layoutsAggregatedHash;
    private int assetsAggregatedHash;
    private int otherAggregatedHash;

    //ResourceData
    private int numberDifferentDrawables;
    private int numberDifferentLayouts;
    private int pngDrawables;
    private int ninePatchDrawables;
    private int jpgDrawables;
    private int gifDrawables;
    private int xmlDrawables;
    private int ldpiDrawables;
    private int mdpiDrawables;
    private int hdpiDrawables;
    private int xhdpiDrawables;
    private int xxhdpiDrawables;
    private int xxxhdpiDrawables;
    private int nodpiDrawables;
    private int tvdpiDrawables;
    private int unspecifiedDpiDrawables;

    // ClassPathData
    private List<String> packageClasses;
    // combined hash of all pacakge classes
    private int packageClassesAggregatedHash;
    private int numberPackageClasses;
    // combined hash of all other classes
    private int otherClassesAggregatedHash;
    private int numberOtherClasses;

    public ServerSideAppData(AppDetailData appDetailData, String deviceId) {

        androidId = deviceId;
        this.hash = hash;

        analysisMode = appDetailData.getAnalysisMode();

        // GeneralData
        GeneralData generalData = appDetailData.getGeneralData();
        packageName = generalData.getPackageName();
        applicationName = generalData.getApplicationName();
        versionName = generalData.getVersionName();
        versionCode = generalData.getVersionCode();
        source = generalData.getSource();
        apkSize = generalData.getApkSize();
        minSdkVersion = generalData.getMinSdkVersion();
        targetSdkVersion = generalData.getTargetSdkVersion();

        // CertificateData
        CertificateData certificateData = appDetailData.getCertificateData();
        signAlgorithm = certificateData.getSignAlgorithm();
        startDate = certificateData.getStartDate();
        endDate = certificateData.getStartDate();
        publicKeyMd5 = certificateData.getPublicKeyMd5();
        certMd5 = certificateData.getCertMd5();
        serialNumber = certificateData.getSerialNumber();
        issuerName = certificateData.getIssuerName();
        issuerOrganization = certificateData.getIssuerOrganization();
        issuerCountry = certificateData.getIssuerCountry();
        subjectName = certificateData.getSubjectName();
        subjectOrganization = certificateData.getSubjectOrganization();
        subjectCountry = certificateData.getSubjectCountry();


        // Activities
        List<ActivityData> activityData = appDetailData.getActivityData();
        numberActivities = activityData.size();
        activityNames = new ArrayList<>(activityData.size());
        for (ActivityData aData : activityData) {
            activityNames.add(aData.getName());
        }
        activitiesAggregatedHash = HashCodeHelper.hashList(activityData);

        // Services
        List<ServiceData> serviceData = appDetailData.getServiceData();
        numberServices = serviceData.size();
        serviceNames = new ArrayList<>(serviceData.size());
        for (ServiceData sData : serviceData) {
            serviceNames.add(sData.getName());
        }
        servicesAggregatedHash = HashCodeHelper.hashList(serviceData);

        // Content Providers
        List<ContentProviderData> providerData = appDetailData.getContentProviderData();
        numberContentProviders = providerData.size();
        contentProviderNames = new ArrayList<>(providerData.size());
        for (ContentProviderData cData : providerData) {
            contentProviderNames.add(cData.getName());
        }
        providersAggregatedHash = HashCodeHelper.hashList(providerData);

        // Broadcast Receivers
        List<BroadcastReceiverData> receiverData = appDetailData.getBroadcastReceiverData();
        numberBroadcastReceivers = receiverData.size();
        broadcastReceiverNames = new ArrayList<>(receiverData.size());
        for (BroadcastReceiverData rData : receiverData) {
            broadcastReceiverNames.add(rData.getName());
        }
        receiversAggregatedHash = HashCodeHelper.hashList(receiverData);

        // Defined permissions
        definedPermissions = appDetailData.getPermissionData().getDefinesPermissions();
        definedPermAggregatedHash = HashCodeHelper.hashList(definedPermissions);
        numberDefinedPermissions = definedPermissions.size();

        // Used permissions
        usedPermissions = appDetailData.getPermissionData().getUsesPermissions();
        usedPermAggregatedHash = HashCodeHelper.hashList(usedPermissions);
        numberUsedPermissions = usedPermissions.size();

        // Features
        List<FeatureData> featureData = appDetailData.getFeatureData();
        numberFeatures = featureData.size();
        featuresAggregatedHash = HashCodeHelper.hashList(featureData);
        featureNames = new ArrayList<>(featureData.size());
        for (FeatureData fData : featureData) {
            featureNames.add(fData.getName());
        }

        // FileData
        FileData fileData = appDetailData.getFileData();
        dexHash = fileData.getDexHash();
        arscHash = fileData.getArscHash();

        drawableHashes = fileData.getOnlyHash(fileData.getDrawableHashes());
        layoutHashes = fileData.getOnlyHash(fileData.getLayoutHashes());
        assetHashes = fileData.getOnlyHash(fileData.getAssetHashes());
        otherHashes = fileData.getOnlyHash(fileData.getOtherHashes());

        numberDrawables = drawableHashes.size();
        numberLayouts = layoutHashes.size();
        numberAssets = assetHashes.size();
        numberOthers = otherHashes.size();

        drawablesAggregatedHash = HashCodeHelper.hashList(drawableHashes);
        layoutsAggregatedHash = HashCodeHelper.hashList(layoutHashes);
        assetsAggregatedHash = HashCodeHelper.hashList(assetHashes);
        otherAggregatedHash = HashCodeHelper.hashList(otherHashes);

        //ResourceData
        ResourceData resourceData = appDetailData.getResourceData();
        numberDifferentDrawables = resourceData.getDifferentDrawables();
        numberDifferentLayouts = resourceData.getDifferentLayouts();
        pngDrawables = resourceData.getPngDrawables();
        ninePatchDrawables = resourceData.getNinePatchDrawables();
        jpgDrawables = resourceData.getJpgDrawables();
        gifDrawables = resourceData.getGifDrawables();
        xmlDrawables = resourceData.getXmlDrawables();
        ldpiDrawables = resourceData.getLdpiDrawables();
        mdpiDrawables = resourceData.getMdpiDrawables();
        hdpiDrawables = resourceData.getHdpiDrawables();
        xhdpiDrawables = resourceData.getXhdpiDrawables();
        xxhdpiDrawables = resourceData.getXxhdpiDrawables();
        xxxhdpiDrawables = resourceData.getXxxhdpiDrawables();
        nodpiDrawables = resourceData.getNodpiDrawables();
        tvdpiDrawables = resourceData.getTvdpiDrawables();
        unspecifiedDpiDrawables = resourceData.getUnspecifiedDpiDrawables();

        // ClassPathData
        ClassPathData classPathData = appDetailData.getClassPathData();

        packageClasses = classPathData.getPackageClasses();
        numberPackageClasses = packageClasses.size();
        packageClassesAggregatedHash = HashCodeHelper.hashList(packageClasses);
        numberOtherClasses = classPathData.getOtherClasses().size();
        otherClassesAggregatedHash = HashCodeHelper.hashList(classPathData.getOtherClasses());

        hash = computeOverallHash();
    }

    private int computeOverallHash() {
        int result = 0;
        result = 31 * result + (packageName != null ? packageName.hashCode() : 0);
        result = 31 * result + (applicationName != null ? applicationName.hashCode() : 0);
        result = 31 * result + (versionName != null ? versionName.hashCode() : 0);
        result = 31 * result + versionCode;
        result = 31 * result + (int) (apkSize ^ (apkSize >>> 32));
        result = 31 * result + minSdkVersion;
        result = 31 * result + targetSdkVersion;
        result = 31 * result + (publicKeyMd5 != null ? publicKeyMd5.hashCode() : 0);
        result = 31 * result + (certMd5 != null ? certMd5.hashCode() : 0);
        result = 31 * result + numberActivities;
        result = 31 * result + activitiesAggregatedHash;
        result = 31 * result + numberServices;
        result = 31 * result + servicesAggregatedHash;
        result = 31 * result + numberContentProviders;
        result = 31 * result + providersAggregatedHash;
        result = 31 * result + numberBroadcastReceivers;
        result = 31 * result + receiversAggregatedHash;
        result = 31 * result + numberDefinedPermissions;
        result = 31 * result + definedPermAggregatedHash;
        result = 31 * result + numberUsedPermissions;
        result = 31 * result + usedPermAggregatedHash;
        result = 31 * result + numberFeatures;
        result = 31 * result + featuresAggregatedHash;
        result = 31 * result + (dexHash != null ? dexHash.hashCode() : 0);
        result = 31 * result + (arscHash != null ? arscHash.hashCode() : 0);
        result = 31 * result + numberDrawables;
        result = 31 * result + numberLayouts;
        result = 31 * result + numberAssets;
        result = 31 * result + numberOthers;
        result = 31 * result + drawablesAggregatedHash;
        result = 31 * result + layoutsAggregatedHash;
        result = 31 * result + assetsAggregatedHash;
        result = 31 * result + otherAggregatedHash;
        result = 31 * result + numberDifferentDrawables;
        result = 31 * result + numberDifferentLayouts;
        result = 31 * result + pngDrawables;
        result = 31 * result + ninePatchDrawables;
        result = 31 * result + jpgDrawables;
        result = 31 * result + gifDrawables;
        result = 31 * result + xmlDrawables;
        result = 31 * result + ldpiDrawables;
        result = 31 * result + mdpiDrawables;
        result = 31 * result + hdpiDrawables;
        result = 31 * result + xhdpiDrawables;
        result = 31 * result + xxhdpiDrawables;
        result = 31 * result + xxxhdpiDrawables;
        result = 31 * result + nodpiDrawables;
        result = 31 * result + tvdpiDrawables;
        result = 31 * result + unspecifiedDpiDrawables;
        result = 31 * result + packageClassesAggregatedHash;
        result = 31 * result + numberPackageClasses;
        result = 31 * result + otherClassesAggregatedHash;
        result = 31 * result + numberOtherClasses;
        return result;
    }
}
