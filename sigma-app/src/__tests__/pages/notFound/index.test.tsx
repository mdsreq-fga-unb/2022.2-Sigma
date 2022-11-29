import { render, screen } from '@testing-library/react'
import NotFound from 'pages/notFound'

describe('NotFound', () => {
    let notFound
    beforeEach(() => {
        notFound = render(<NotFound />)
    })
    test('should render page', () => {
        const h1 = screen.getByRole('heading')
        expect(h1).toBeTruthy()
    })
})
