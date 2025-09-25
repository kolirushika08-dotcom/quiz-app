import React, {useEffect, useState} from 'react';
import axios from 'axios';

export default function Quiz(){
  const [questions, setQuestions] = useState([]);
  const [answers, setAnswers] = useState({});
  const [score, setScore] = useState(null);

  useEffect(()=>{
    axios.get('/api/quiz/questions').then(r => setQuestions(r.data)).catch(console.error);
  },[]);

  function select(qid, oid){
    setAnswers(prev => ({...prev, [qid]: oid}));
  }

  function submit(){
    axios.post('/api/quiz/submit', { answers }).then(r => {
      setScore(r.data.score);
    }).catch(console.error);
  }

  return (
    <div>
      <h2>Take Quiz</h2>
      {questions.map(q => (
        <div key={q.id} style={{ marginBottom: 16 }}>
          <div><strong>{q.text}</strong></div>
          <div>
            {q.options && q.options.map(o => (
              <label key={o.id} style={{ display: 'block' }}>
                <input type="radio" name={'q'+q.id} onChange={()=>select(q.id, o.id)} /> {o.text}
              </label>
            ))}
          </div>
        </div>
      ))}
      <button onClick={submit}>Submit</button>
      {score !== null && <div><strong>Your score: {score}</strong></div>}
    </div>
  );
}
