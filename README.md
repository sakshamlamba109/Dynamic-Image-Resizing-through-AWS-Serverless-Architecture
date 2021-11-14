# Minor-Project
This is the minor project-1
In this we are building a serverless architecture using AWS Lambda, S3 bucket and AWS API Gateway
It will be a web application that would resize images in a fly!
Methadology:
The user will upload the image into the S3 bucket. As soon as the user clicks resie button, the api in the API gateway gets initiated.

1) Abstract:
"A picture is worth a thousand words" is a common adage around the world meaning that a single still image can convey multiple ideas and its meaning more effectively than a verbal description. Before images, the internet was a text-only tundra. Today images are an integral part of any website. They serve both as eye candy as well as informational tools. 
With the explosion of device types used to access the Internet with different capabilities, screen sizes, and resolutions, developers must provide images in an array of sizes to ensure a great user experience. Our project intends to simplify this by leveraging AWS cloud services and its serverless architecture.

2) Intoduction:
Let's assume that our forum application got very popular and we wanted to let our users upload their profile pictures. In different parts of our forum, we will be using different sizes of profile pictures, so whenever a user uploads a new picture, we should resize the image on a data storage. 
Since the outrage of Covid 19, the pandemic has led all the work to be in online mode, whether it be filling out some important application forms and documents or the increased involvement of social media.
And in the growing world of social media, people require this kind of tool to produce resized images without losing the quality of the uploaded image. For the same we will create java code that will be implemented through Lambda function to respond to the HTTP upload request and will resize images on the fly. Also, with this architecture, we are again binding the HTTP context with our business context; it means our Lambda function will have to deal with two different contexts.
AWS brings a better solution to this problem. With API Gateway, we can expose a limited functionality of our cloud resources end user, and we can trigger Lambda functions when some specific events occur in the cloud resources.
In our project, we would be implementing serverless architecture using AWS services  Lambda: Lambda designed to run code pieces responding to external or internal cloud events, without needing to set up any infrastructure upfront, and it brought a real, managed, and pay-per-go infrastructure model.
S3: Amazon Simple Storage Service is basically an object storage service which offers data availability, security, and performance.
AWS API Gateway: It is a fully managed service used by developers for their application to access data, functionality from backend services. ] through which the users can bring modification in any uploaded images like resizing, cropping, with other functionalities. For this, we will be using Java as a primary coding language. For the algorithm part, we will be using imgscalr, Java Image-Scaling Library implementing Chris Campbell's incremental scaling algorithm that would maintain the quality of the image after performing the required operations. To optimize our serverless architecture the concept of adaptive function placement algorithm is also used.





