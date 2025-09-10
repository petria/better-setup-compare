import React, {createContext, ReactNode, useContext, useEffect, useState} from 'react';

interface User {
    username: string;
    roles: { authority: string }[];
}

interface AuthContextType {
    isAuthenticated: boolean;
    user: User | null;
    loading: boolean;
    login: (username: string, password: string) => Promise<void>;
    logout: () => Promise<void>;
}

const AuthContext = createContext<AuthContextType>(null!);

export const AuthProvider = ({children}: { children: ReactNode }) => {
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [user, setUser] = useState<User | null>(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const checkAuth = async () => {
            try {
                const response = await fetch('/api/user');
                if (response.ok) {
                    const userData = await response.json();
                    setUser(userData);
                    setIsAuthenticated(true);
                }
            } catch (error) {
                console.error('Error checking auth status:', error);
            } finally {
                setLoading(false);
            }
        };
        checkAuth();
    }, []);

    const login = async (username: string, password: string) => {
        const response = await fetch('/login', {
            method: 'POST',
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            body: new URLSearchParams({username, password})
        });

        if (response.ok) {
            const userResponse = await fetch('/api/user');
            const userData = await userResponse.json();
            setUser(userData);
            setIsAuthenticated(true);
        } else {
            throw new Error('Login failed');
        }
    };

    const logout = async () => {
        await fetch('/logout', {method: 'POST'});
        setIsAuthenticated(false);
        setUser(null);
    };

    return (
        <AuthContext.Provider value={{isAuthenticated, user, loading, login, logout}}>
            {children}
        </AuthContext.Provider>
    );
};

export const useAuth = () => {
    return useContext(AuthContext);
};