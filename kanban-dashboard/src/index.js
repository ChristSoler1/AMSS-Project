import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import 'bootstrap/dist/css/bootstrap.css';
import 'react-toastify/dist/ReactToastify.css';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);

// Wenn Sie die Leistung in Ihrer Anwendung messen möchten, übergeben Sie eine Funktion,
// um Ergebnisse zu protokollieren (zum Beispiel: reportWebVitals(console.log))
// oder senden Sie diese zu einem Analysepunkt. Erfahren Sie mehr: https://bit.ly/CRA-vitals
reportWebVitals();
