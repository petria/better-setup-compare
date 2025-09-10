import React from 'react';
import {Navigate, useLocation} from 'react-router-dom';
import {useAuth} from '../hooks/useAuth';

const PrivateRoute = ({children}: { children: React.ReactElement }) => {
    const auth = useAuth();
    const location = useLocation();

    if (auth.loading) {
        return <div>Loading...</div>;
    }

    if (!auth.isAuthenticated) {
        return <Navigate to="/login" state={{from: location}} replace/>;
    }

    return children;
};

export default PrivateRoute;