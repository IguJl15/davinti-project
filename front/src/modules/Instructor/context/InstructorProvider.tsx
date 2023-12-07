import { PropsWithChildren, createContext, useContext, useEffect, useState } from 'react';
import { useAuth } from '../../../core/hooks/useAuth';
import { InstructorActions } from './InstructorActionsContext';

const InstructorActionsContext = createContext<InstructorActions>({} as InstructorActions);

export default function InstructorProvider({ children }: PropsWithChildren) {
  const { authData } = useAuth();
  const [actions, setActions] = useState(new InstructorActions(authData!));

  useEffect(() => {
    return () => {
      if (authData) setActions(new InstructorActions(authData));
    };
  }, [authData]);

  return (
    <InstructorActionsContext.Provider value={actions}>
      {children}
    </InstructorActionsContext.Provider>
  );
}

export function useInstructorActions() {
  return useContext(InstructorActionsContext);
}
