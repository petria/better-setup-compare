import React from 'react';
import './App.css';
import Hello from './components/Hello';
import LoginPage from './components/LoginPage';
import PrivateRoute from './components/PrivateRoute';
import {AuthProvider} from './hooks/useAuth';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import {Container} from 'react-bootstrap';
import Navigation from './components/Navigation';
import AdminPage from './components/AdminPage';
import AdminRoute from './components/AdminRoute';
import CompareSetups from './components/CompareSetups';

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
                                <Hello/>
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
                        path="/compare-setups"
                        element={<PrivateRoute>
                            <CompareSetups/>
                        </PrivateRoute>}
                    />
                </Routes>
            </Container>
        </>
    );
}

export default App;
