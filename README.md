# Dynamic-Image-Resizing-through-AWS-Serverless-Architecture
In this project, we are building a serverless architecture using AWS Lambda, S3 bucket and AWS API Gateway
It will be a web application that would resize images in a fly!
Methadology:
The user will upload the image into the S3 bucket. As soon as the user clicks resie button, the api in the API gateway gets initiated.

# Abstract:
"A picture is worth a thousand words" is a common adage around the world meaning that a single still image can convey multiple ideas and its meaning more effectively than a verbal description. Before images, the internet was a text-only tundra. Today images are an integral part of any website. They serve both as eye candy as well as informational tools. 
With the explosion of device types used to access the Internet with different capabilities, screen sizes, and resolutions, developers must provide images in an array of sizes to ensure a great user experience. Our project intends to simplify this by leveraging AWS cloud services and its serverless architecture.

# Intoduction:
Let's assume that our forum application got very popular and we wanted to let our users upload their profile pictures. In different parts of our forum, we will be using different sizes of profile pictures, so whenever a user uploads a new picture, we should resize the image on a data storage. 
Since the outrage of Covid 19, the pandemic has led all the work to be in online mode, whether it be filling out some important application forms and documents or the increased involvement of social media.
And in the growing world of social media, people require this kind of tool to produce resized images without losing the quality of the uploaded image. For the same we will create java code that will be implemented through Lambda function to respond to the HTTP upload request and will resize images on the fly. Also, with this architecture, we are again binding the HTTP context with our business context; it means our Lambda function will have to deal with two different contexts.
AWS brings a better solution to this problem. With API Gateway, we can expose a limited functionality of our cloud resources end user, and we can trigger Lambda functions when some specific events occur in the cloud resources.
In our project, we would be implementing serverless architecture using AWS services  Lambda: Lambda designed to run code pieces responding to external or internal cloud events, without needing to set up any infrastructure upfront, and it brought a real, managed, and pay-per-go infrastructure model.
S3: Amazon Simple Storage Service is basically an object storage service which offers data availability, security, and performance.
AWS API Gateway: It is a fully managed service used by developers for their application to access data, functionality from backend services. ] through which the users can bring modification in any uploaded images like resizing, cropping, with other functionalities. For this, we will be using Java as a primary coding language. For the algorithm part, we will be using imgscalr, Java Image-Scaling Library implementing Chris Campbell's incremental scaling algorithm that would maintain the quality of the image after performing the required operations. To optimize our serverless architecture the concept of adaptive function placement algorithm is also used.

# Problem Statement:
Social media is more than just something we do on the side; it's an integral part of society today. And now, you can make money on social media by becoming an influencer or content creator. First impressions matter, so it's important you have a nice appearance on your social media. Do you ever go to upload an image to social media and realize it’s not the right size? Our Image Resizer allows you to resize your photo for a variety of different social media channels, such as Facebook, Twitter, and Instagram. So no more awkward cropping. You can upload a photo in the required size to your official documents, like a job application or exam application.

# Objectives
Here we are providing the optimised resized images as the basis of the uploaded sample images. Our system will produce the resized image with the implementation of Serverless architecture using AWS. The use of this architecture will help us to know more about their client needs more effectively and thus improvise their thinking towards customer satisfaction which will give them a wide range of operations.
Sub-Objective:
•   We use Java image scaling algorithm for different image scaling techniques.
•   Implementing Serverless architecture by using AWS lambda having the automatic scaling, built-in high availability, and a pay-for-use billing model to increase agility and optimize costs.
•   AWS S3 storage for uploaded images.

# Methodology
AWS Lambda is a serverless computing service provided by Amazon Web Services (AWS). Users of AWS Lambda create functions, self-contained applications written in one of the supported languages and runtimes, and upload them to AWS Lambda, which executes those functions in an efficient and flexible manner. 
This service will allow users to upload images and resize them without worrying about quality, as we will be using the Java Image-Scaling Library implementing Chris Campbell's incremental scaling algorithm for better quality. The image will be stored in an AWS S3 bucket before being resized.

   The overall process of the Serverless Architecture for Image resizing:
•   Step 1: Image initially will be stored in the S3 bucket as soon as the user clicks the upload button.
•   Step 2: Request is transferred to the API Gateway when the resize button is clicked by the user.
•   Step 3: The Lambda function is triggered by the API. 
•   Step 4: Image will be fetched from the S3 bucket by the lambda function written in Java, and the image will be resized as specified by the user.
		Methods for Resizer class in Java:
•	createUrl: When we create the unique URL for our images, we use the file name, if there is any and add the hash of the full URL as well as the width and height to the URL in the 'readImage' method.
•	readImage: We'll use Java image IO class to read the original image into a buffered image.
•	resizeImage: We'll use image scaler to resize the original image to the desired size.
•	storeImage: It will return the input stream of the resized image to the 'storeImage' method where it will create a temporary file.
•	alreadyExists: In the Lambda container containing the resized image. It will store that file in the S3 bucket. It will then delete the temporary file. The 'alreadyExists' method will check if the resized image for the requested size and original image URL already exists, so we don't need to resize the same image to the same size multiple times.
•   Step 5: Resized image is displayed back to the user.

# Diagrams Used:
1) Use Case Diagram
2) State-Chart Diagram
3) Architecture Diagram

# Cloud deployment Platform
-> Amazon Web Services








