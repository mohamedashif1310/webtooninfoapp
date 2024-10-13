# Path for the README.md file
readme_file_path = os.path.join(project_folder, 'README.md')

# Content for the README.md file
readme_content = """
# Webtoon Info App

The **Webtoon Info App** is an Android application that allows users to browse a curated list of webtoon titles, view detailed information, save their favorite webtoons, and rate them. The app is designed for webtoon enthusiasts who want to explore fantasy manhwa and maintain a list of their favorites.

## Features

1. **Home Screen**  
   - Displays a list of webtoon titles inspired by "The 50 Best Fantasy Manhwa You Must Read Now."
   - Each webtoon title includes an image and a brief description.

2. **Detail Screen**  
   - When a webtoon title is clicked, users are navigated to a detail screen inspired by "Lore Olympus Webtoon: Jaw Dropping Seen on Media."
   - Includes a larger image, a detailed description, and a button to “Add to Favorites.”
   
3. **Favorites Screen**  
   - Users can view their saved favorites.
   - Webtoon favorites are stored using the Room Database to ensure persistence when the app is closed and reopened.

4. **Interactive Features**  
   - Users can rate each webtoon (1-5 stars).
   - The average rating is displayed dynamically on the detail screen.

## Getting Started

To get a local copy of the project up and running, follow these steps:

### Prerequisites

- Android Studio installed
- Java 8 or higher
- Android SDK

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/webtooninfoapp.git
