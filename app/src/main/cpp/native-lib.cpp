#include <jni.h>
#include <string>
#include <iostream>

//#include <GL/glut.h>
//
//GLuint matToTexture(cv::Mat mat, GLenum minFilter,
//                    GLenum magFilter, GLenum wrapFilter){
//// Generate a number for our textureID's unique handle
//    GLuint textureID;
//    glGenTextures(1, &textureID);
//
//// Bind to our texture handle
//    glBindTexture(GL_TEXTURE_2D, textureID);
//
//// Catch silly-mistake texture interpolation method for magnification
//    if (magFilter == GL_LINEAR_MIPMAP_LINEAR ||
//        magFilter == GL_LINEAR_MIPMAP_NEAREST ||
//        magFilter == GL_NEAREST_MIPMAP_LINEAR ||
//        magFilter == GL_NEAREST_MIPMAP_NEAREST)
//    {
//        cout << "You can't use MIPMAPs for magnification - setting filter to GL_LINEAR" << endl;
//        magFilter = GL_LINEAR;
//    }
//
//// Set texture interpolation methods for minification and magnification
//    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, minFilter);
//    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, magFilter);
//
//// Set texture clamping method
//    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, wrapFilter);
//    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, wrapFilter);
//
//// Set incoming texture format to:
//// GL_BGR for CV_CAP_OPENNI_BGR_IMAGE,
//// GL_LUMINANCE for CV_CAP_OPENNI_DISPARITY_MAP,
//// Work out other mappings as required ( there's a list in comments in main() )
//    GLenum inputColourFormat = GL_BGR_EXT;
//    if (mat.channels() == 1)
//    {
//        inputColourFormat = GL_LUMINANCE;
//    }
//
//// Create the texture
//    glTexImage2D(GL_TEXTURE_2D, // Type of texture
//                 0, // Pyramid level (for mip-mapping) - 0 is the top level
//                 GL_RGB, // Internal colour format to convert to
//                 mat.cols, // Image width i.e. 640 for Kinect in standard mode
//                 mat.rows, // Image height i.e. 480 for Kinect in standard mode
//                 0, // Border width in pixels (can either be 1 or 0)
//                 inputColourFormat, // Input image format (i.e. GL_RGB, GL_RGBA, GL_BGR etc.)
//                 GL_UNSIGNED_BYTE, // Image data type
//                 mat.ptr()); // The actual image data itself
//
//// If we're using mipmaps then generate them. Note: This requires OpenGL 3.0 or higher
//    if (minFilter == GL_LINEAR_MIPMAP_LINEAR ||
//        minFilter == GL_LINEAR_MIPMAP_NEAREST ||
//        minFilter == GL_NEAREST_MIPMAP_LINEAR ||
//        minFilter == GL_NEAREST_MIPMAP_NEAREST)
//    {
//        glGenerateMipmap(GL_TEXTURE_2D);
//    }
//
//    return textureID;
//}

//unsigned char* getPixelData( int x1, int y1, int x2, int y2 )
//{
//    int y_low, y_hi;
//    int x_low, x_hi;
//
//    if ( y1 < y2 )
//    {
//        y_low = y1;
//        y_hi  = y2;
//    }
//    else
//    {
//        y_low = y2;
//        y_hi  = y1;
//    }
//
//    if ( x1 < x2 )
//    {
//        x_low = x1;
//        x_hi  = x2;
//    }
//    else
//    {
//        x_low = x2;
//        x_hi  = x1;
//    }
//
//    while ( glGetError() != GL_NO_ERROR )
//    {
//        ;
//    }
//
//    glReadBuffer( GL_BACK_LEFT );
//
//    glDisable( GL_TEXTURE_2D );
//
//    glPixelStorei( GL_PACK_ALIGNMENT, 1 );
//
//    unsigned char *data = new unsigned char[ ( x_hi - x_low + 1 ) * ( y_hi - y_low + 1 ) * 3 ];
//
//    glReadPixels( x_low, y_low, x_hi-x_low+1, y_hi-y_low+1, GL_RGB, GL_UNSIGNED_BYTE, data );
//
//    if ( glGetError() != GL_NO_ERROR )
//    {
//        delete[] data;
//        return 0;
//    }
//    else
//    {
//        return data;
//    }
//}

//CvSize size = cvSize( 320, 240 );
//
//unsigned char *pixel_buf = getPixelData( 0, 0, size.width - 1, size.height - 1 );
//
//if ( pixel_buf == 0 )
//return 0;
//
//IplImage *result = cvCreateImage( size, IPL_DEPTH_8U, 3 );
//memcpy( result->imageData, pixel_buf, size.width * size.height * 3 );
//delete[] pixel_buf;