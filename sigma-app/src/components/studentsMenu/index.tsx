import { Link, Outlet } from 'react-router-dom'

export default function StudentsMenu() {
    const routes = [
        {
            to: '/home',
            label: 'Voltar ao menu principal',
        },
        {
            to: '/alunos',
            label: 'Page 1',
        },
        {
            to: 'page2',
            label: 'Page 2',
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
