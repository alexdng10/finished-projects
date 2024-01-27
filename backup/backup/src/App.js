import React, { useState } from 'react';
import axios from 'axios';

const App = () => {
  const [inputText, setInputText] = useState('');
  const [imageFilenames, setImageFilenames] = useState([]);
  
  const fetchImages = async () => {
    try {
      // Send user input to the Flask backend
      const response = await axios.post('http://127.0.0.1:5000/api/images', { user_input: inputText });
      // Update state with the filenames returned from the backend
      setImageFilenames(response.data.image_paths);
    } catch (error) {
      console.error('Error fetching images:', error);
    }
  };

  return (
    <div>
      <input 
        type="text" 
        value={inputText} 
        onChange={(e) => setInputText(e.target.value)} 
        placeholder="Enter your text"
      />
      <button onClick={fetchImages}>Get Images</button>
      <div>
                {imageFilenames.map((filename, index) => (
            <img key={index} src={`http://127.0.0.1:5000${filename}`} alt={`User Requested ${index}`} />
          ))}
      </div>
    </div>
  );
};

export default App;