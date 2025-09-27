import React, {useEffect} from 'react';
import './App.css';
import LoginPage from './components/LoginPage';
import PrivateRoute from './components/PrivateRoute';
import {AuthProvider, useAuth} from './hooks/useAuth';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import {Container} from 'react-bootstrap';
import Navigation from './components/Navigation';
import AdminPage from './components/AdminPage';
import AdminRoute from './components/AdminRoute';
import CompareSetups from './components/CompareSetups';
import AiChat from './components/AiChat';

const App: React.FC = () => {
    return (
        <AuthProvider>
            <Router>
                <AppContent/>
            </Router>
        </AuthProvider>
    );
}

const AppContent: React.FC = () => {
    const auth = useAuth();
    useEffect(() => {
        auth.checkAuth();
    }, []);

    if (auth.loading) {
        return <div>Loading...</div>;
    }

    return (
        <>
            <Navigation/>
            <Container className="mt-5">
                <Routes>
                    <Route path="/login" element={<LoginPage/>}/>
                    <Route
                        path="/"
                        element={
                            <PrivateRoute>
                                <AiChat/>
                            </PrivateRoute>
                        }
                    />
                    <Route
                        path="/admin"
                        element={
                            <AdminRoute>
                                <AdminPage/>
                            </AdminRoute>
                        }
                    />
                    <Route
                        path="/compare-setupDtos"
                        element={<PrivateRoute>
                            <CompareSetups/>
                        </PrivateRoute>}
                    />
                    <Route
                        path="/ai-chat"
                        element={<PrivateRoute>
                            <AiChat/>
                        </PrivateRoute>}
                    />
                </Routes>
            </Container>
        </>
    );
}

export default App;
