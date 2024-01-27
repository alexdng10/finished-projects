import React, { useState, useEffect } from 'react';

function Question() {
    const [quizData, setQuizData] = useState([]);
    const [selectedAnswers, setSelectedAnswers] = useState({});
    const [fetchCounter, setFetchCounter] = useState(0); // Counter to trigger new fetches

    // Function to fetch quiz data
    const fetchQuizData = async () => {
        try {
            const response = await fetch('http://127.0.0.1:5000/quiz'); // Adjust if your endpoint URL is different
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            const data = await response.json();
            setQuizData(data);
            setSelectedAnswers({}); // Reset selected answers when new data is fetched
        } catch (error) {
            console.error('Error fetching quiz data:', error);
        }
    };

    // useEffect to fetch data when component mounts and when fetchCounter changes
    useEffect(() => {
        fetchQuizData();
    }, [fetchCounter]);

    // Handler for selecting an option
    const handleOptionSelect = (questionIndex, optionIndex) => {
        setSelectedAnswers({
            ...selectedAnswers,
            [questionIndex]: optionIndex
        });
    };

    // Handler for fetching new quiz data
    const handleNextQuiz = () => {
        setFetchCounter(prev => prev + 1);
    };

    return (
        <div>
            {quizData.length > 0 ? quizData.map((item, questionIndex) => (
                <div key={questionIndex} className="question-block">
                    <p className="question">{item.question}</p>
                    <ul className="options">
                        {item.options.map((option, optionIndex) => (
                            <li key={optionIndex} 
                                className={`option ${selectedAnswers[questionIndex] === optionIndex ? 'selected' : ''}`}
                                onClick={() => handleOptionSelect(questionIndex, optionIndex)}>
                                {option}
                            </li>
                        ))}
                    </ul>
                    <p className="correct-answer">
                        {selectedAnswers[questionIndex] !== undefined ? 
                         `Your answer: ${item.options[selectedAnswers[questionIndex]]}` : ''}
                    </p>
                </div>
            )) : <p>Loading...</p>}
            <button onClick={handleNextQuiz}>Next Questions</button>
        </div>
    );
}

export default Question;