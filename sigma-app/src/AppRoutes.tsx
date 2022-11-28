import Classes from 'pages/classes'
import Home from 'pages/home'
import Register from 'pages/register'
import Students from 'pages/students'
import Page1 from 'pages/students/page1'
import Page2 from 'pages/students/page2'
import Teachers from 'pages/teachers'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import Login from './pages/login'
import NotFound from './pages/notFound'

function AppRoutes() {
    return (
        <Router>
            <Routes>
                <Route path='/' element={<Login />} />
                <Route path='/cadastro' element={<Register />} />
                <Route path='/home' element={<Home />} />
                <Route path='/alunos' element={<Students />}>
                    <Route index element={<Page1 />} />
                    <Route path='page2' element={<Page2 />} />
                </Route>
                <Route path='/professores' element={<Teachers />} />
                <Route path='/turmas' element={<Classes />} />
                <Route path='*' element={<NotFound />} />
            </Routes>
        </Router>
    )
}

export default AppRoutes
