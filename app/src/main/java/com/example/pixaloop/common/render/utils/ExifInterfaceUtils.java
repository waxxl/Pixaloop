package com.example.pixaloop.common.render.utils;

import android.media.ExifInterface;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.HashMap;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class ExifInterfaceUtils {
    public static final ImmutableList<String> a = ImmutableList.of("ApertureValue", "Artist", "BitsPerSample", "BrightnessValue", "CFAPattern", "ColorSpace", "ComponentsConfiguration", "CompressedBitsPerPixel", "Compression", "Contrast", "Copyright", "CustomRendered", "DateTime", "DateTimeDigitized", "DateTimeOriginal", "DefaultCropSize", "DeviceSettingDescription", "DigitalZoomRatio", "DNGVersion", "ExifVersion", "ExposureBiasValue", "ExposureIndex", "ExposureMode", "ExposureProgram", "ExposureTime", "FileSource", "Flash", "FlashpixVersion", "FlashEnergy", "FocalLength", "FocalLengthIn35mmFilm", "FocalPlaneResolutionUnit", "FocalPlaneXResolution", "FocalPlaneYResolution", "FNumber", "GainControl", "GPSAltitude", "GPSAltitudeRef", "GPSAreaInformation", "GPSDateStamp", "GPSDestBearing", "GPSDestBearingRef", "GPSDestDistance", "GPSDestDistanceRef", "GPSDestLatitude", "GPSDestLatitudeRef", "GPSDestLongitude", "GPSDestLongitudeRef", "GPSDifferential", "GPSDOP", "GPSHPositioningError", "GPSImgDirection", "GPSImgDirectionRef", "GPSLatitude", "GPSLatitudeRef", "GPSLongitude", "GPSLongitudeRef", "GPSMapDatum", "GPSMeasureMode", "GPSProcessingMethod", "GPSSatellites", "GPSSpeed", "GPSSpeedRef", "GPSStatus", "GPSTimeStamp", "GPSTrack", "GPSTrackRef", "GPSVersionID", "ImageDescription", "ImageLength", "ImageUniqueID", "ImageWidth", "InteroperabilityIndex", "ISOSpeed", "JPEGInterchangeFormat", "JPEGInterchangeFormatLength", "LightSource", "Make", "MakerNote", "MaxApertureValue", "MeteringMode", "Model", "NewSubfileType", "OECF", "AspectFrame", "PreviewImageLength", "PreviewImageStart", "ThumbnailImage", "Orientation", "PhotometricInterpretation", "PixelXDimension", "PixelYDimension", "PlanarConfiguration", "PrimaryChromaticities", "ReferenceBlackWhite", "RelatedSoundFile", "ResolutionUnit", "RowsPerStrip", "ISO", "JpgFromRaw", "SensorBottomBorder", "SensorLeftBorder", "SensorRightBorder", "SensorTopBorder", "SamplesPerPixel", "Saturation", "SceneCaptureType", "SceneType", "SensingMethod", "Sharpness", "ShutterSpeedValue", "Software", "SpatialFrequencyResponse", "SpectralSensitivity", "StripByteCounts", "StripOffsets", "SubfileType", "SubjectArea", "SubjectDistance", "SubjectDistanceRange", "SubjectLocation", "SubSecTime", "SubSecTimeDigitized", "SubSecTimeOriginal", "ThumbnailImageLength", "ThumbnailImageWidth", "TransferFunction", "UserComment", "WhiteBalance", "WhitePoint", "XResolution", "YCbCrCoefficients", "YCbCrPositioning", "YCbCrSubSampling", "YResolution");

    @NotNull
    public static final HashMap<String, String> a(@NotNull ExifInterface exifInterface) {
        Intrinsics.checkNotNull(exifInterface, "receiver$0");
        HashMap<String, String> hashMap = new HashMap<>();
        UnmodifiableIterator<String> it = a.iterator();
        while (it.hasNext()) {
            String next = it.next();
            String a2 = exifInterface.getAttribute(next);
            if (a2 != null) {
                Intrinsics.checkNotNull((Object) next, "exifTag");
                hashMap.put(next, a2);
            }
        }
        return hashMap;
    }

    @NotNull
    public static final ImageOrientation b(@NotNull ExifInterface exifInterface) {
        Intrinsics.checkNotNull(exifInterface, "receiver$0");
        switch (exifInterface.getAttributeInt("Orientation", -1)) {
            case 1:
                return ImageOrientation.NORMAL;
            case 2:
                return ImageOrientation.FLIP_HORIZONTAL;
            case 3:
                return ImageOrientation.ROTATE_180;
            case 4:
                return ImageOrientation.FLIP_VERTICAL;
            case 5:
                return ImageOrientation.TRANSPOSE;
            case 6:
                return ImageOrientation.ROTATE_90;
            case 7:
                return ImageOrientation.TRANSVERSE;
            case 8:
                return ImageOrientation.ROTATE_270;
            default:
                return ImageOrientation.NORMAL;
        }
    }
}
