import React from 'react';
import {Navigate, useLocation} from 'react-router-dom';
import {useAuth} from '../hooks/useAuth';

const AdminRoute = ({children}: { children: React.ReactElement }) => {
    const auth = useAuth();
    const location = useLocation();

    if (auth.loading) {
        return <div>Loading...</div>;
    }

    const isAdmin = auth.user?.roles.some(role => role.authority === 'ROLE_ADMIN');

    if (!auth.isAuthenticated) {
        return <Navigate to="/login" state={{from: location}} replace/>;
    }

    if (!isAdmin) {
        return <Navigate to="/" state={{from: location}} replace/>;
    }

    return children;
};

export default AdminRoute;