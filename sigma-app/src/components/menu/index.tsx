import { Link, Outlet } from 'react-router-dom'

export default function Menu() {
    const routes = [
        {
            to: '/alunos',
            label: 'Alunos',
        },
        {
            to: '/professores',
            label: 'Professores',
        },
        {
            to: '/turmas',
            label: 'Turmas',
        },
        {
            to: '/',
            label: 'Sair',
        },
    ]
    return (
        <>
            <nav>
                <ul>
                    {routes.map((route, index) => (
                        <li key={index}>
                            <Link to={route.to}>{route.label}</Link>
                        </li>
                    ))}
                </ul>
            </nav>
            <Outlet />
        </>
    )
}
