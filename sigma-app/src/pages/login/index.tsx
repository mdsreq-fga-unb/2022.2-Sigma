import { useNavigate } from 'react-router-dom'

export default function Login() {
    const navigate = useNavigate()
    return (
        <>
            <button onClick={() => navigate('home')}>Entrar</button>
            <button onClick={() => navigate('cadastro')}>Cadastrar</button>
        </>
    )
}
