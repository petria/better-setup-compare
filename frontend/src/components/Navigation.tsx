import React from 'react';
import {Container, Nav, Navbar, NavDropdown} from 'react-bootstrap';
import {useAuth} from '../hooks/useAuth';
import {Link, useNavigate} from 'react-router-dom';

const Navigation: React.FC = () => {
    const auth = useAuth();
    const navigate = useNavigate();

    const handleLogout = () => {
        auth.logout().then(() => navigate('/login'));
    };

    const isAdmin = auth.user?.roles.some(role => role.authority === 'ROLE_ADMIN');

    return (
        <Navbar bg="dark" variant="dark" expand="lg">
            <Container>
                <Navbar.Brand as={Link} to="/">Application</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav"/>
                <Navbar.Collapse id="basic-navbar-nav">

                    {auth.isAuthenticated &&
                        <Nav className="me-auto">
                            <Nav.Link as={Link} to="/compare-setupDtos">Compare setups</Nav.Link>
                            <Nav.Link as={Link} to="/ai-chat">Ai Chat</Nav.Link>
                            {isAdmin && <Nav.Link href="/admin">Admin</Nav.Link>}
                        </Nav>
                    }
                    {auth.isAuthenticated &&
                        <Nav>
                            <NavDropdown title={auth.user?.username} id="basic-nav-dropdown">
                                <NavDropdown.Item href="#action/3.1">Your profile</NavDropdown.Item>
                                <NavDropdown.Item href="#action/3.2">Your repositories</NavDropdown.Item>
                                <NavDropdown.Item href="#action/3.3">Your projects</NavDropdown.Item>
                                <NavDropdown.Item href="#action/3.4">Your stars</NavDropdown.Item>
                                <NavDropdown.Divider/>
                                <NavDropdown.Item onClick={handleLogout}>Sign out</NavDropdown.Item>
                            </NavDropdown>
                        </Nav>
                    }
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
};

export default Navigation;