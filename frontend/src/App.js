import React from 'react';
import Quiz from './components/Quiz';
import Admin from './components/Admin';

export default function App(){
  return (
    <div style={{ padding: 20, fontFamily: 'Arial, sans-serif' }}>
      <h1>Quiz App</h1>
      <div style={{ display: 'flex', gap: 40 }}>
        <div style={{ flex: 2 }}>
          <Quiz />
        </div>
        <div style={{ flex: 1 }}>
          <Admin />
        </div>
      </div>
    </div>
  );
}
