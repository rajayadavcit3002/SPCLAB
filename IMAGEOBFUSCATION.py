import io
from google.cloud import vision

def obfuscate_image(image_path):

    # Authenticate with Google Cloud Vision API
    client = vision.ImageAnnotatorClient()

    # Read the image file
    with io.open(image_path, 'rb') as image_file:
        content = image_file.read()

    # Create a Vision API image object
    image = vision.Image(content=content)

    # Apply blurring to obfuscate the image
    response = client.safe_search_detection(image=image)

    blurred_image = response.full_text_annotation

    # Save the obfuscated image
    output_path = 'obfuscated_image.jpg'
    blurred_image.save(output_path, 'JPEG')

    return output_path


# Set the path to the image file
image_path = 'original_image.jpg'

# Obfuscate the image
obfuscated_image_path = obfuscate_image(image_path)

# Print the path to the obfuscated image
print('Obfuscated image path:', obfuscated_image_path)
