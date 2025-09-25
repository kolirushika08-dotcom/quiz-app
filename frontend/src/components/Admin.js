import React, {useState, useEffect} from 'react';
import axios from 'axios';

export default function Admin(){
  const [questions, setQuestions] = useState([]);
  const [qtext, setQtext] = useState('');
  const [opts, setOpts] = useState([{text:'', isCorrect:false},{text:'', isCorrect:false}]);

  useEffect(()=>{ load(); },[]);

  function load(){ axios.get('/api/admin/questions').then(r=>setQuestions(r.data)).catch(console.error); }

  function addOption(){ setOpts([...opts, {text:'', isCorrect:false}]); }
  function setOpt(i, key, val){ const copy = [...opts]; copy[i][key]=val; setOpts(copy); }
  function save(){
    const payload = { text: qtext, options: opts };
    axios.post('/api/admin/questions', payload).then(()=>{ setQtext(''); setOpts([{text:'',isCorrect:false},{text:'',isCorrect:false}]); load(); }).catch(console.error);
  }
  function remove(id){ axios.delete('/api/admin/questions/'+id).then(load).catch(console.error); }

  return (
    <div>
      <h2>Admin</h2>
      <div>
        <h4>Add question</h4>
        <textarea value={qtext} onChange={e=>setQtext(e.target.value)} rows={3} style={{width:'100%'}} />
        <div>
          {opts.map((o,i)=>(
            <div key={i}>
              <input placeholder="Option text" value={o.text} onChange={e=>setOpt(i,'text',e.target.value)} />
              <label style={{marginLeft:8}}>
                <input type="checkbox" checked={o.isCorrect} onChange={e=>setOpt(i,'isCorrect',e.target.checked)} /> Correct
              </label>
            </div>
          ))}
          <button onClick={addOption}>Add option</button>
        </div>
        <button onClick={save}>Save Question</button>
      </div>

      <hr/>
      <h4>Existing Questions</h4>
      {questions.map(q=>(
        <div key={q.id} style={{ marginBottom: 12 }}>
          <div><strong>{q.text}</strong> <button onClick={()=>remove(q.id)}>Delete</button></div>
          <ul>
            {q.options && q.options.map(o=> <li key={o.id}>{o.text} {o.isCorrect ? '(correct)' : ''}</li>)}
          </ul>
        </div>
      ))}
    </div>
  );
}
