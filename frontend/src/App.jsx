import { useState } from 'react'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import Home from "./page/Home.jsx";
import AcidentFetcher from "./page/AcidentFetcher.jsx";
import Navbar from "./components/Navbar.jsx";

function App() {
  const [count, setCount] = useState(0)

    return (
        <Router>
            <Navbar />
            <Routes>
                <Route path="/AcidentList" element={<AcidentFetcher/>}/>
                <Route path="/" element={<Home />} />
            </Routes>
        </Router>

  )
}

export default App
